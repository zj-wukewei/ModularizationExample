package com.wkw.archives.dialog;

import com.wkw.archives.R;
import com.wkw.archives.databinding.ArchivesDialogTestBinding;
import com.wkw.uiframework.widget.dialog.BaseDataBindingDialog;

/**
 * @author GoGo on 2019-08-24.
 */
public class TestDialog extends BaseDataBindingDialog<ArchivesDialogTestBinding> {
    @Override
    public int getLayoutRes() {
        return R.layout.archives_dialog_test;
    }

    @Override
    public void bindView() {

    }

    @Override
    public int getWidthPadding() {
        return 50;
    }

    @Override
    public boolean isBottom() {
        return true;
    }

    @Override
    public String getFragmentTag() {
        return "TestDialog";
    }
}
