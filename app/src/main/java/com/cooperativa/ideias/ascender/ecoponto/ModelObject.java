package com.cooperativa.ideias.ascender.ecoponto;

import com.cooperativa.ideias.ascender.ecoponto.R;

// Classe enum ModelObjeto para listar todas as Activitys ViewPages...
public enum ModelObject {
    // Listas com todos os Layouts das ViewPages...
    RED( R.string.red, R.layout.view_red),
    BLUE(R.string.blue, R.layout.view_blue),
    GREEN(R.string.green, R.layout.view_green);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
