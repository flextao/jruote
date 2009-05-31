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
 * See OpenWFE::Extras::ProcessErrorTables (openwfe/extras/expool/db_errorjournal)
 * <pre>
 * create_table :process_errors do |t|
 *   t.column :created_at, :timestamp
 *   t.column :wfid, :string, :null => false
 *   t.column :expid, :string, :null => false
 *   t.column :svalue, :text, :null => false
 *     # 'value' could be reserved, using 'svalue' instead
 *     # It stands for 'serialized value'.
 * end
 * add_index :process_errors, :created_at
 * add_index :process_errors, :wfid
 * add_index :process_errors, :expid
 * </pre>
 */

@Table(name = "process_errors")
@Entity
public class ProcessError {
    private long id;
    private Date createdAt;
    private String wfid;
    private String expid;
    private String svalue;

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Column(name = "created_at")
    @Index(name = "index_created_at")
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setWfid(String wfid) {
        this.wfid = wfid;
    }
    @Index(name = "index_wfid")
    public String getWfid() {
        return wfid;
    }
    public void setExpid(String expid) {
        this.expid = expid;
    }
    @Index(name = "index_expid")
    public String getExpid() {
        return expid;
    }
    public void setSvalue(String svalue) {
        this.svalue = svalue;
    }

    @Column(columnDefinition = "text")
    public String getSvalue() {
        return svalue;
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
