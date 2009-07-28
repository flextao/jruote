/******************************************************************************
    Copyright (c) 2009 Flextao

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
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
    private Integer id;
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

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
}
