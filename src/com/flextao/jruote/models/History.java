/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.jruote.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * See: OpenWFE::Extras::HistoryTables (openwfe/extras/expool/db_history)
 * 
 * <pre>
 * create_table :history do |t|
 *   t.column :created_at, :timestamp
 *   t.column :source, :string, :null =&gt; false
 *   t.column :event, :string, :null =&gt; false
 *   t.column :wfid, :string
 *   t.column :wfname, :string
 *   t.column :wfrevision, :string
 *   t.column :fei, :string
 *   t.column :participant, :string
 *   t.column :message, :string # empty is ok
 * end
 * 
 * add_index :history, :created_at
 * add_index :history, :source
 * add_index :history, :event
 * add_index :history, :wfid
 * add_index :history, :wfname
 * add_index :history, :wfrevision
 * add_index :history, :participant
 * </pre>
 */
@Table(name = "history")
@Entity
public class History {
    private long id;
    private Date createdAt;
    private String source;
    private String event;
    private String wfid;
    private String wfname;
    private String wfrevision;
    private String fei;
    private String participant;
    private String message;

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "created_at")
    @Index(name = "index_created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column(nullable = false)
    @Index(name = "index_source")
    public String getSource() {
        return source;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Column(nullable = false)
    @Index(name = "index_event")
    public String getEvent() {
        return event;
    }

    public void setWfid(String wfid) {
        this.wfid = wfid;
    }

    @Index(name = "index_wfid")
    public String getWfid() {
        return wfid;
    }

    public void setWfname(String wfname) {
        this.wfname = wfname;
    }

    @Index(name = "index_wfname")
    public String getWfname() {
        return wfname;
    }

    public void setWfrevision(String wfrevision) {
        this.wfrevision = wfrevision;
    }

    @Index(name = "index_wfrevision")
    public String getWfrevision() {
        return wfrevision;
    }

    public void setFei(String fei) {
        this.fei = fei;
    }

    public String getFei() {
        return fei;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    @Index(name = "index_participant")
    public String getParticipant() {
        return participant;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
}
