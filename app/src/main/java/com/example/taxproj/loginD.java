package com.example.taxproj;

public class loginD
{
    String pan,pass,name,mob;

public loginD(String pan, String pass,String name,String mob)
        {
        this.pan=pan;
        this.pass=pass;
        this.name=name;
this.mob=mob;
        }

public String getPan() { return pan; }

public void setPan(String pan) { this.pan = pan;}
public String getPass(){ return pass; }

public void setPass(String pass) {
        this.pass = pass;
        }
       public String getName(){ return name;}

        public void setName(String name) {
                this.name = name;
        }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }
}



