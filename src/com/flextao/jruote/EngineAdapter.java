/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.jruote;

import static org.jruby.javasupport.JavaEmbedUtils.initialize;
import static org.jruby.javasupport.JavaEmbedUtils.terminate;
import static com.flextao.jruote.Helpers.read;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

import com.flextao.jruote.models.ArWorkitem;
import com.spinn3r.log5j.Logger;

/**
 * Ruby ruote engine adapter.
 */
public class EngineAdapter {
    private static final Logger log = Logger.getLogger();

    private Ruby runtime;
    private JRubyObject engine;
    private boolean ready;

    public EngineAdapter(InputStream engineDefinition) throws IOException {
        this(read(engineDefinition));
    }

    public EngineAdapter(String engineDefinition) {
        this(initialize(new ArrayList<String>()), engineDefinition);
    }

    public EngineAdapter(Ruby runtime, InputStream engineDefinition) throws IOException {
        this(runtime, read(engineDefinition));
    }

    public EngineAdapter(Ruby runtime, String engineDefinitionScript) {
        log.info("initializing engine adapter");
        this.runtime = runtime;
        log.info("loading ruote");
        require("openwfe");
        log.info("loading ruote extentions");
        require("openwfe/contextual_ext");
        require("openwfe/workitem_ext");
        require("openwfe/extras/participants/ar_workitem_ext");
        require("openwfe/extras/participants/sync_java_participant_adapter");
        log.info("eval engine difinition script...");
        engine = syncEvalScript(engineDefinitionScript);
        log.info("initialized engine adapter");
    }

    public void require(String path) {
        this.runtime.getLoadService().require(path);
    }

    public void registerSyncParticipant(String name, Participant participant) {
        JRubyObject syncJavaParticipantAdapterClass = syncEvalScript("OpenWFE::Extras::SyncJavaParticipantAdapter");
        Participant adapter = syncJavaParticipantAdapterClass.send("new", participant);
        registerParticipant(name, adapter);
    }

    public void registerParticipant(String name, Participant participant) {
        log.info("registering participant /%s/", name);
        engine.send("register_participant", name, participant);
    }

    public synchronized void ready() {
        log.info("reloading engine");
        engine.send("reload");
        ready = true;
        log.info("reloaded engine, ready");
    }

    /**
     * Don't call it, unless you really want to do it
     */
    public synchronized void stop() {
        log.info("stopping engine, please wait 5 seconds...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // we don't care what's happen here;
        }
        ready = false;
        engine.send("stop");
        engine = null;
        log.info("engine stopped.");
        log.info("Terminating ruby runtime");
        terminate(runtime);
        log.info("Ruby runtime terminated.");
    }

    public void clearARConnections() {
        log.info("clear all ActiveRecord connections");
        syncEvalScript("ActiveRecord::Base.clear_all_connections!");
    }

    public void launch(URL processDefinitionURL) {
        launch(processDefinitionURL, null);
    }

    public void launch(URL processDefinitionURL, String variables) {
        launch(processDefinitionURL.getPath(), variables);
    }

    public void launch(String processURI, String variables) {
        launch(processURI, launchVariables(variables));
    }

    public void launch(String processURI, JRubyObject options) {
        shouldBeReady();
        log.info("launching process [%s] with options [%s]", processURI, options);
        engine.send("launch", processURI, options);
    }

    public void reply(ArWorkitem workItem) {
        reply(syncEvalScript("OpenWFE::Extras::ArWorkitem.convert_to_in_flow_work_item(" + workItem.getId() + ")"));
    }

    public void reply(IRubyObject workItem) {
        reply(new JRubyObject(workItem));
    }

    public void reply(JRubyObject workItem) {
        shouldBeReady();
        log.debug("reply work item [%s]", workItem);
        engine.send("reply", workItem);
    }

    public void cancelProcess(ArWorkitem workItem) {
        cancelProcess(workItem.getWfid());
    }

    public void cancelProcess(String wfid) {
        shouldBeReady();
        log.debug("cancel process by wfid[%s]", wfid);
        engine.send("cancel_process", wfid);
    }

    public JRubyObject syncEvalScript(String script) {
        return new JRubyObject(runtime.evalScriptlet(script));
    }

    private JRubyObject launchVariables(String variables) {
        if (variables == null) {
            return syncEvalScript("{}");
        }
        return syncEvalScript("{:variables => {" + variables + "}}");
    }

    private synchronized void shouldBeReady() {
        if (!ready) {
            throw new IllegalStateException("Engine is not ready yet. Please call 'ready' method if engine is ready to go.");
        }
    }
}
