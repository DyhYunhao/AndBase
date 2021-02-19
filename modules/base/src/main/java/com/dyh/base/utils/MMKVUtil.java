package com.dyh.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.getkeepsafe.relinker.ReLinker;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;

import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.Set;

/**
 * describe: mmkv的封装
 * create by daiyh on 2021-2-5
 */
public class MMKVUtil {

    private volatile static MMKVUtil instance;
    private Context mContext;
    private MMKV mmkv = MMKV.defaultMMKV();
    private boolean isCrypt; //是否加密
    private String cryptKey; //密钥
    private boolean isMigrate; //是否迁移SharedPreference旧数据

    private MMKVUtil() {
    }

    public static MMKVUtil getInstance() {
        if (instance == null) {
            synchronized (MMKVUtil.class) {
                if (instance == null) {
                    instance = new MMKVUtil();
                }
            }
        }
        return instance;
    }

    //mmkv使用自己设定不同的存储目录做code19版本的适配，这个在github官网有说明
    @SuppressLint("ObsoleteSdkInt")
    public void initDir(Context context) {
        mContext = context;
        String root = context.getFilesDir().getAbsolutePath() + "/mmkv";
        if (Build.VERSION.SDK_INT == 19) {
            MMKV.initialize(root, libName -> ReLinker.loadLibrary(context, libName));
        } else {
            MMKV.initialize(context);
        }
    }

    //设置日志等级
    public void setLogLevel(MMKVLogLevel level) {
        MMKV.setLogLevel(level);
    }

    //是否需要加密解密
    public void setCrypt(boolean isCrypt, String cryptKey) {
        this.isCrypt = isCrypt;
        this.cryptKey = cryptKey;
    }

    //是否迁移旧数据
    public void setMigrate(boolean isMigrate) {
        this.isMigrate = isMigrate;
    }

    //无指定ID调用
    public MMKV getMmkv() {
        return getMmkv(null);
    }

    //是否定义ID
    public MMKV getMmkv(String name) {
        MMKV mmkv;
        if (TextUtils.isEmpty(name)) {
            mmkv = isCrypt ? MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, cryptKey) :
                    MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null);
        } else {
            mmkv = isCrypt ? MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE, cryptKey) :
                    MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE, null);
        }
        if (isMigrate) {
            //迁移SP数据
            SharedPreferences sharedPreferences;
            if (TextUtils.isEmpty(name)) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            } else {
                sharedPreferences = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
            }
            mmkv.importFromSharedPreferences(sharedPreferences);
            sharedPreferences.edit().clear().apply();
        }
        return mmkv;
    }

    //---String Set类型---------------------------
    public Set<String> getStringSet (String key) {
        return getMmkv().getStringSet(key, new HashSet<>());
    }

    public Set<String> getStringSet (String key, Set<String> defValues) {
        return getMmkv().getStringSet(key, defValues);
    }

    public Set<String> getStringSet (String name, String key, Set<String> defValue) {
        return getMmkv(name).getStringSet(key, defValue);
    }

    public void putStringSet (String key, Set<String> value) {
        getMmkv().putStringSet(key, value);
    }

    public void putStringSet (String name, String key, Set<String> value) {
        getMmkv(name).putStringSet(key, value);
    }

    //---double类型---------------------------
    public double getDouble (String key) {
        return getMmkv().decodeDouble(key);
    }

    public double getDouble (String key, double defValue) {
        return getMmkv().decodeDouble(key, defValue);
    }

    public double getDouble (String name, String key, double defValue) {
        return getMmkv(name).decodeDouble(key, defValue);
    }

    public void putDouble (String key, double value) {
        getMmkv().encode(key, value);
    }

    //---byte[]类型-------------------------------------
    public void putByte (String key, byte[] value) {
        getMmkv().encode(key, value);
    }

    public byte[] getBytes (String key) {
        return getMmkv().decodeBytes(key);
    }

    public byte[] getBytes (String key, byte[] defValue) {
        return getMmkv().decodeBytes(key, defValue);
    }

    public byte[] getBytes (String name, String key, byte[] defValue) {
        return getMmkv(name).decodeBytes(key, defValue);
    }

    //---String类型-----------------------------------
    public String getString(String key) {
        return getMmkv().getString(key, "");
    }

    public String getString(String key, String defValue) {
        return getMmkv().getString(key, defValue);
    }

    public String getString(String name, String key, String defValue) {
        return getMmkv(name).getString(key, defValue);
    }

    public void putString(String key, String value) {
        getMmkv().putString(key, value);
    }

    public void putString(String name, String key, String value) {
        getMmkv(name).putString(key, value);
    }

    //---Boolean类型-----------------------------------
    public boolean getBoolean(String key) {
        return getMmkv().getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return getMmkv().getBoolean(key, defValue);
    }

    public boolean getBoolean(String name, String key, boolean defValue) {
        return getMmkv(name).getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        getMmkv().putBoolean(key, value);
    }

    public void putBoolean(String name, String key, boolean value) {
        getMmkv(name).putBoolean(key, value);
    }

    //---Int类型-----------------------------------
    public int getInt(String key) {
        return getMmkv().getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return getMmkv().getInt(key, defValue);
    }

    public int getInt(String name, String key, int defValue) {
        return getMmkv(name).getInt(key, defValue);
    }

    public void putInt(String key, int value) {
        getMmkv().putInt(key, value);
    }

    public void putInt(String name, String key, int value) {
        getMmkv(name).putInt(key, value);
    }

    //---Float类型-----------------------------------
    public float getFloat(String key) {
        return getMmkv().getFloat(key, 0f);
    }

    public float getFloat(String key, float defValue) {
        return getMmkv().getFloat(key, defValue);
    }

    public float getFloat(String name, String key, float defValue) {
        return getMmkv(name).getFloat(key, defValue);
    }

    public void putFloat(String key, float value) {
        getMmkv().putFloat(key, value);
    }

    public void putFloat(String name, String key, float value) {
        getMmkv(name).putFloat(key, value);
    }

    //---Long类型-----------------------------------
    public long getLong(String key) {
        return getMmkv().getLong(key, 0L);
    }

    public long getLong(String key, long defValue) {
        return getMmkv().getLong(key, defValue);
    }

    public long getLong(String name, String key, long defValue) {
        return getMmkv(name).getLong(key, defValue);
    }

    public void putLong(String key, long value) {
        getMmkv().putLong(key, value);
    }

    public void putLong(String name, String key, long value) {
        getMmkv(name).putLong(key, value);
    }

    //其他方法
    public void remove(String key) {
        getMmkv().remove(key);
    }

    public void remove(String name, String key) {
        getMmkv(name).remove(key);
    }

    public void clear() {
        getMmkv().clear();
    }

    public void clear(String name) {
        getMmkv(name).clear();
    }

    public boolean contains(String key) {
        return getMmkv().contains(key);
    }

    public boolean contains(String name, String key) {
        return getMmkv(name).contains(key);
    }
}
