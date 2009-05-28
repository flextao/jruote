/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.jruote.models;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.jruby.util.ByteList;
import org.jvyamlb.YAML;

/**
 * should be same with the following definition of table see:
 * OpenWFE::Extras::ArWorkitemTables
 * (openwfe/extras/participants/ar_participants)
 * 
 * <pre>
 *  create_table :ar_workitems do |t|
 *    t.column :fei, :string
 *    t.column :wfid, :string
 *    t.column :expid, :string
 *    t.column :wfname, :string
 *    t.column :wfrevision, :string
 *    t.column :participant_name, :string
 *    t.column :store_name, :string
 *    t.column :dispatch_time, :timestamp
 *    t.column :last_modified, :timestamp
 *    t.column :wi_fields, :text
 *    t.column :activity, :string
 *    t.column :keywords, :text
 *  end
 * 
 *  add_index :ar_workitems, :fei, :unique =&gt; true
 *  add_index :ar_workitems, :wfid
 *  add_index :ar_workitems, :expid
 *  add_index :ar_workitems, :wfname
 *  add_index :ar_workitems, :wfrevision
 *  add_index :ar_workitems, :participant_name
 *  add_index :ar_workitems, :store_name
 * </pre>
 */
@Table(name = "ar_workitems")
@Entity
public class ArWorkitem {

    private static final String DEFAULT_ENCODING = "UTF-8";

    private long id;
    private String fei;
    private String wfid;
    private String expid;
    private String wfname;
    private String wfrevision;
    private String participantName;
    private String storeName;
    private Date dispatchTime;
    private Date lastModified;
    private String wiFields;
    private String activity;
    private String keywords;

    @Column(unique = true)
    @Index(name = "index_fei")
    public String getFei() {
        return fei;
    }

    public void setFei(String fei) {
        this.fei = fei;
    }

    @Index(name = "index_wfid")
    public String getWfid() {
        return wfid;
    }

    public void setWfid(String wfid) {
        this.wfid = wfid;
    }

    @Index(name = "index_expid")
    public String getExpid() {
        return expid;
    }

    public void setExpid(String expid) {
        this.expid = expid;
    }

    @Index(name = "index_wfname")
    public String getWfname() {
        return wfname;
    }

    public void setWfname(String wfname) {
        this.wfname = wfname;
    }

    @Index(name = "index_wfrevision")
    public String getWfrevision() {
        return wfrevision;
    }

    public void setWfrevision(String wfrevision) {
        this.wfrevision = wfrevision;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Column(columnDefinition = "text")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    @Column(name = "participant_name")
    @Index(name = "index_participant_name")
    public String getParticipantName() {
        return participantName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Column(name = "store_name")
    @Index(name = "index_store_name")
    public String getStoreName() {
        return storeName;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    @Column(name = "dispatch_time")
    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Column(name = "last_modified")
    public Date getLastModified() {
        return lastModified;
    }

    public void setWiFields(String wiFields) {
        this.wiFields = wiFields;
    }

    @Column(name = "wi_fields", columnDefinition = "text")
    public String getWiFields() {
        return wiFields;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Transient
    public Map<String, Object> wiFieldsMap() {
        Map<Object, Object> map = (Map<Object, Object>) YAML.load(toInputStream(getWiFields()));
        return convertByteListObjects(map);
    }

    private Map<String, Object> convertByteListObjects(Map<Object, Object> map) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (map != null) {
            for (Object key : map.keySet()) {
                Object javaKey = toStringIfItIsByteList(key);
                Object javaValue = toStringIfItIsByteList(map.get(key));
                result.put(javaKey.toString(), javaValue);
            }
        }
        return result;
    }

    private Object toStringIfItIsByteList(Object key) {
        return key instanceof ByteList ? key.toString() : key;
    }

    private ByteArrayInputStream toInputStream(String fields) {
        try {
            return new ByteArrayInputStream(fields.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
