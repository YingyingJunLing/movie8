package com.bw.movie.mvp.view.frag;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.UpdateHeadPicBean;
import com.bw.movie.mvp.presenter.presenterimpl.MyPresenter;
import com.bw.movie.mvp.presenter.presenterimpl.UpdateHeadPicPresenter;
import com.bw.movie.mvp.view.activity.MyAttentionActivity;
import com.bw.movie.mvp.view.activity.MyFeedDBActivity;
import com.bw.movie.mvp.view.activity.MyMessageActivity;
import com.bw.movie.mvp.view.activity.MyRecordActivity;
import com.bw.movie.mvp.view.activity.MySysMsgActivity;
import com.bw.movie.mvp.view.activity.MyVersionActivity;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Frag_My extends BaseFragment<Contract.IUpdateHeadPicView, UpdateHeadPicPresenter> implements Contract.IUpdateHeadPicView, View.OnClickListener {

    private String nickName;
    private String headPic;
    private View view;
    private static final int PHOTO_REQUEST_CAREMA=1;//拍照
    private static final int PHOTO_REQUEST_GALLERY=2;//从相册中选择
    private static final int PHOTO_REQUEST_CUT=3;//裁剪之后
    private String path= Environment.getExternalStorageDirectory()+"/header_image.png";
    private File file;
    private static final String PHOTO_FILE_MAME="header_image.jpg";//临时文件名
    private Dialog dialog;
    private UpdateHeadPicPresenter updateHeadPicPresenter;
    private String userId;
    private String sessionId;
    private HashMap<String, String> hashMap;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        view = View.inflate(getActivity(), R.layout.frag_my, null);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getLoginData( LoginBean.ResultBean resultBean)
    {
        userId = resultBean.getUserId();
        sessionId = resultBean.getSessionId();
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getUserInfo(LoginBean.ResultBean.UserInfoBean userInfoBean){
        headPic = userInfoBean.getHeadPic();
        nickName = userInfoBean.getNickName();
    }

    @Override
    protected void initFragmentChildView(View view) {
        hashMap = new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("sessionId",sessionId);
        TextView myName = view.findViewById(R.id.my_name);
        ImageView myHead = view.findViewById(R.id.my_head);
        myHead.setOnClickListener(this);
        ImageView sysMsg = view.findViewById(R.id.sysMsg);
        sysMsg.setOnClickListener(this);
        view.findViewById(R.id.my_message_image).setOnClickListener(this);
        view.findViewById(R.id.my_attention_image).setOnClickListener(this);
        view.findViewById(R.id.my_record_image).setOnClickListener(this);
        view.findViewById(R.id.my_feeddb_image).setOnClickListener(this);
        view.findViewById(R.id.my_version_image).setOnClickListener(this);

        myName.setText(nickName);
        Glide.with(getActivity())
                .load(headPic)
                .into(myHead);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected UpdateHeadPicPresenter createPresenter() {

        updateHeadPicPresenter = new UpdateHeadPicPresenter();
        return updateHeadPicPresenter;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.my_message_image:
                startActivity(new Intent(getActivity(),MyMessageActivity.class));
                break;
            case  R.id.my_attention_image:
                startActivity(new Intent(getActivity(),MyAttentionActivity.class));
                break;
            case  R.id.my_record_image:
                startActivity(new Intent(getActivity(),MyRecordActivity.class));
                break;
            case  R.id.my_feeddb_image:
                startActivity(new Intent(getActivity(),MyFeedDBActivity.class));
                break;
            case  R.id.my_version_image:
                startActivity(new Intent(getActivity(),MyVersionActivity.class));
                break;
            case  R.id.sysMsg:
                startActivity(new Intent(getActivity(), MySysMsgActivity.class));
                break;
            case  R.id.my_head:
                dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
                //填充对话框的布局
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);
                //初始化控件
                inflate.findViewById(R.id.choosePhoto).setOnClickListener(this);
                inflate.findViewById(R.id.takePhoto).setOnClickListener(this);
                inflate.findViewById(R.id.btn_cancel).setOnClickListener(this);
                //将布局设置给Dialog
                dialog.setContentView(inflate);
                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                if(dialogWindow == null){
                    return;
                }
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity(Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 20;//设置Dialog距离底部的距离
                //将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
                break;
            case R.id.takePhoto:
                //打开相机
                Intent intent_takePhoto=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent_takePhoto, PHOTO_REQUEST_CAREMA);

                dialog.dismiss();
                break;
            case R.id.choosePhoto:
                Intent intent_choosePhoto=new Intent(Intent.ACTION_PICK);
                intent_choosePhoto.setType("image/*");
                startActivityForResult(intent_choosePhoto,PHOTO_REQUEST_GALLERY);

                dialog.dismiss();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
        }
    }
    //裁剪图片
    private void crop(Uri uri){
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        //支持裁剪
        intent.putExtra("CROP",true);
        //裁剪的比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        //裁剪后输出图片的尺寸大小
        intent.putExtra("outputX",250);
        intent.putExtra("outputY",250);
        //将图片返回给data
        intent.putExtra("return-data",true);
        startActivityForResult(intent,PHOTO_REQUEST_CUT);
    }
    //判断SD卡是否挂载
    public boolean hasSdcard(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==PHOTO_REQUEST_CAREMA&&resultCode==getActivity().RESULT_OK){//相机返回的数据
            if (hasSdcard()){
                Bitmap bitmap = data.getParcelableExtra("data");
                //将bitmap转换为uri
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null,null));

                String[] proj = { MediaStore.Images.Media.DATA };

                Cursor actualimagecursor = getActivity().managedQuery(uri,proj,null,null,null);

                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                actualimagecursor.moveToFirst();

                String img_path = actualimagecursor.getString(actual_image_column_index);

                File file = new File(img_path);
                List<File> list=new ArrayList<>();
                list.add(file);
                // myHead.setImageBitmap(bitmap);
                updateHeadPicPresenter.onIUpdateHeadPicPre(hashMap,file);

            }else{
                Toast.makeText(getActivity(), "未找到存储啦，无法存储照片", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode==PHOTO_REQUEST_GALLERY&&resultCode==getActivity().RESULT_OK){//相册返回的数据
            //得到图片的全路径
            if (data!=null){
                Uri uri = data.getData();
                String[] proj = { MediaStore.Images.Media.DATA };
                Cursor actualimagecursor = getActivity().managedQuery(uri,proj,null,null,null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                String img_path = actualimagecursor.getString(actual_image_column_index);
                File file = new File(img_path);
                List<File> list=new ArrayList<>();
                list.add(file);
                updateHeadPicPresenter.onIUpdateHeadPicPre(hashMap,file);
            }
        }else if(requestCode==PHOTO_REQUEST_CUT&&resultCode==getActivity().RESULT_OK){//剪切回来的照片数据
            if (data!=null){
                Bitmap bitmap = data.getParcelableExtra("data");
                //将bitmap转换为uri
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null,null));

                String[] proj = { MediaStore.Images.Media.DATA };

                Cursor actualimagecursor = getActivity().managedQuery(uri,proj,null,null,null);

                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                actualimagecursor.moveToFirst();

                String img_path = actualimagecursor.getString(actual_image_column_index);

                File file = new File(img_path);
                List<File> list=new ArrayList<>();
                list.add(file);
                updateHeadPicPresenter.onIUpdateHeadPicPre(hashMap,file);
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onIUpdateHeadPicSuccess(Object o) {
        if(o instanceof UpdateHeadPicBean)
        {
            UpdateHeadPicBean updateHeadPicBean = (UpdateHeadPicBean) o;
            Log.e("updateHeadPicBean",updateHeadPicBean+"");
            if(updateHeadPicBean != null)
            {
                Toast.makeText(getActivity(),updateHeadPicBean.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onIUpdateHeadPicFail(String errorInfo) {

    }
}
