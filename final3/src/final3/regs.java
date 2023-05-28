/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final3;

/**
 *
 * @author Yuns
 */
public class regs {
    
    private int stdid;
    private int coid;
    private String seme;

    public int getStdid() {
        return stdid;
    }

    public void setStdid(int stdid) {
        this.stdid = stdid;
    }

    public int getCoid() {
        return coid;
    }

    public void setCoid(int coid) {
        this.coid = coid;
    }

    public String getSeme() {
        return seme;
    }

    public void setSeme(String seme) {
        this.seme = seme;
    }

    public regs(int stdid, int coid, String seme) {
        this.stdid = stdid;
        this.coid = coid;
        this.seme = seme;
    }
    
    
    
}