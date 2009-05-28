/******************************************************************************

* 杭州昼韬信息技术有限公司版权所有。

* 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
* 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

***********************************************************************/

package com.flextao.jruote.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * See OpenWFE::Extras::ExpressionTables (openwfe/extras/expool/db_expstorage)
 * <pre>
 * create_table :expressions do |t|
 *   t.column :fei, :string, :null => false
 *   t.column :wfid, :string, :null => false
 *   t.column :expid, :string, :null => false
 *   #t.column :wfname, :string, :null => false
 *   t.column :exp_class, :string, :null => false

 *   #t.column :svalue, :text, :null => false
 *   t.column :svalue, :text, :null => false, :limit => 1024 * 1024
 *     #
 *     # 'value' could be reserved, using 'svalue' instead
 *     #
 *     # :limit patch by Maarten Oelering (a greater value
 *     # could be required in some cases)
 * end
 * add_index :expressions, :fei
 * add_index :expressions, :wfid
 * add_index :expressions, :expid
 * #add_index :expressions, :wfname
 * add_index :expressions, :exp_class
 * </pre>
 */
@Table(name = "expressions")
@Entity
public class Expression {
    private long id;
    private String fei;
    private String wfid;
    private String expid;
    private String expClass;
    private String svalue;

    public void setFei(String fei) {
        this.fei = fei;
    }
    @Column(nullable = false)
    @Index(name = "index_fei")
    public String getFei() {
        return fei;
    }
    public void setWfid(String wfid) {
        this.wfid = wfid;
    }
    @Column(nullable = false)
    @Index(name = "index_wfid")
    public String getWfid() {
        return wfid;
    }
    public void setExpid(String expid) {
        this.expid = expid;
    }
    @Column(nullable = false)
    @Index(name = "index_expid")
    public String getExpid() {
        return expid;
    }
    public void setExpClass(String expClass) {
        this.expClass = expClass;
    }
    @Column(name = "exp_class", nullable = false)
    public String getExpClass() {
        return expClass;
    }
    public void setSvalue(String svalue) {
        this.svalue = svalue;
    }
    @Column(columnDefinition = "text", nullable = false)
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
