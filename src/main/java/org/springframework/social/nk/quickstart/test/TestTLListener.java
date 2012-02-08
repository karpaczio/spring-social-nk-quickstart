package org.springframework.social.nk.quickstart.test;

public class TestTLListener {

    private long ms;
    
    public TestTLListener() {
        ms = System.currentTimeMillis();
    }
    
    @Override
    public String toString() {
        return ms + "";
    }
}
