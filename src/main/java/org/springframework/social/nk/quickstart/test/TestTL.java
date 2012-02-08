package org.springframework.social.nk.quickstart.test;


public class TestTL {

    private final TestTLListener listener;
    
    private static final ThreadLocal<TestTLListener> listeners = new ThreadLocal<TestTLListener>() {
        @Override
        protected TestTLListener initialValue() {
            return new TestTLListener();
        }
    };
    
    public TestTL() {
        listener = listeners.get();
    }
    
    public TestTLListener get() {
        return listener;
    }
}
