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
    private Integer id;
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
    public void setId(Integer id) {
        this.id = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
}
