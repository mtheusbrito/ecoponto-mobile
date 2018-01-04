package com.cooperativa.ideias.ascender.ecoponto.Activity.Activity.Activity;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * Created by Lucas on 07/12/2017.
 */

class FirebaseDatabase {
    private static AtomicMarkableReference instance;

    public static AtomicMarkableReference getInstance() {
        return instance;
    }
}
