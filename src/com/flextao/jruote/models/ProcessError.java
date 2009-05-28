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
