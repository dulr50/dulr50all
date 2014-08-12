/*
 * Copyright (C) 2013 Qunar.Inc All rights reserved.
 */
package com.utils.inject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import com.utils.inject.annotation.Nullable;

import android.content.Context;

public class CheckUtils {
    /**
     * 妫�鏌ヤ换鎰廜bject鏄惁涓虹┖
     * <hr>
     * shallow check : 涓嶄細妫�鏌ュ鍣ㄥ唴閮ㄧ殑鍏冪礌鏄惁涓虹┖
     * @return
     */
    public static boolean isEmpty(Object obj) {

        if (obj == null) {
            return true;
        }

        if (obj instanceof Collection<?>) {
            // 妫�鏌ュ悇绉岰ollection鏄惁涓虹┖(List,Queue,Set)
            return ((Collection<?>) obj).isEmpty();
        } else if (obj instanceof Map<?, ?>) {
            // 妫�鏌ュ悇绉峂ap
            return ((Map<?, ?>) obj).isEmpty();
        } else if (obj instanceof CharSequence) {
            // 妫�鏌ュ悇绉岰harSequence
            return ((CharSequence) obj).length() == 0;
        } else if (obj.getClass().isArray()) {
            // 妫�鏌ュ悇绉峛ase array
            // return Array.getLength(obj) == 0;
            if (obj instanceof Object[]) {
                return ((Object[]) obj).length == 0;
            } else if (obj instanceof int[]) {
                return ((int[]) obj).length == 0;
            } else if (obj instanceof long[]) {
                return ((long[]) obj).length == 0;
            } else if (obj instanceof short[]) {
                return ((short[]) obj).length == 0;
            } else if (obj instanceof double[]) {
                return ((double[]) obj).length == 0;
            } else if (obj instanceof float[]) {
                return ((float[]) obj).length == 0;
            } else if (obj instanceof boolean[]) {
                return ((boolean[]) obj).length == 0;
            } else if (obj instanceof char[]) {
                return ((char[]) obj).length == 0;
            } else if (obj instanceof byte[]) {
                return ((byte[]) obj).length == 0;
            }
        } else if (obj instanceof EmptyCheckable) {
            return ((EmptyCheckable) obj).isEmpty();
        }

        return false;
    }

    /**
     * 鍙栧弽{@link #isEmpty(Object obj)},澶ч噺鎯呭喌涓嬪彲浠ュ皯鍐欎竴涓劅鍙瑰彿
     * <ol>
     * <li>up by naitiz : 1024</li>
     * </ol>
     * @author ran.feng
     * @since 2013骞�12鏈�24鏃ヤ笅鍗�9:59:19
     */
    public static boolean isExist(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isContainsEmpty(Object... objs) {
        if (isEmpty(objs)) {
            return true;
        }
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }


	private static final String SELECT_RUNTIME_PROPERTY = "persist.sys.dalvik.vm.lib";
	private static final String LIB_DALVIK = "libdvm.so";
	private static final String LIB_ART = "libart.so";
	private static final String LIB_ART_D = "libartd.so";


	public static String getCurrentRuntimeValue() {
		try {
			Class<?> systemProperties = Class.forName("android.os.SystemProperties");
			try {
				Method get = systemProperties.getMethod("get", String.class, String.class);
				if (get == null) {
					return "WTF?!";
				}
				try {
					final String value = (String) get.invoke(systemProperties, SELECT_RUNTIME_PROPERTY,
	                        /* Assuming default is */"Dalvik");
					if (LIB_DALVIK.equals(value)) {
						return "Dalvik";
					} else if (LIB_ART.equals(value)) {
						return "ART";
					} else if (LIB_ART_D.equals(value)) {
						return "ART debug build";
					}

					return value;
				} catch (IllegalAccessException e) {
					return "IllegalAccessException";
				} catch (IllegalArgumentException e) {
					return "IllegalArgumentException";
				} catch (InvocationTargetException e) {
					return "InvocationTargetException";
				}
			} catch (NoSuchMethodException e) {
				return "SystemProperties.get(String key, String def) method is not found";
			}
		} catch (Throwable e) {
			return "SystemProperties class is not found";
		}
	}

    /**
     * 鏄惁涓哄鏁�
     * @param i
     * @return
     * @author chaos
     * @since 2013骞�12鏈�31鏃ヤ笅鍗�9:39:02
     */
    public static boolean isOdd(int i) {
        return i % 2 != 0;
    }

    /**
     * 鏄惁涓哄伓鏁�
     * @param i
     * @return
     * @author chaos
     * @since 2013骞�12鏈�31鏃ヤ笅鍗�9:39:08
     */
    public static boolean isEven(int i) {
        return i % 2 == 0;
    }

    /**
     * 妫�鏌ユ灇涓剧粍涓槸鍚﹀寘鍚寚瀹氭灇涓�
     * @param group
     * @param child 涓嶈兘涓虹┖
     * @return
     * @author {Chaos}
     * @since 2014骞�3鏈�12鏃ヤ笅鍗�2:23:45
     */
    public static boolean isContainsEnum(@Nullable Enum<?>[] group, Enum<?> child) {

        if (isEmpty(group)) {
            return false;
        }

        for (Enum<?> enums : group) {
            if (enums == child) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasRootAccess(Context ctx) {
        try {
            if (runScriptAsRoot(ctx, "exit 0") == 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static int runScriptAsRoot(Context ctx, String script) {
        StringBuilder res = new StringBuilder();
        final File file = new File(ctx.getCacheDir(), "secopt.sh");
        final ScriptRunner runner = new ScriptRunner(file, script, res);
        runner.start();
        try {
            runner.join(40000);
            if (runner.isAlive()) {
                runner.interrupt();
                runner.join(150);
                runner.destroy();
                runner.join(50);
            }
        } catch (InterruptedException ex) {
        }
        return runner.exitcode;
    }

    private static final class ScriptRunner extends Thread {
        private final File file;
        private final String script;
        private final StringBuilder res;
        public int exitcode = -1;
        private Process exec;

        public ScriptRunner(File file, String script, StringBuilder res) {
            this.file = file;
            this.script = script;
            this.res = res;
        }

        @Override
        public void run() {
            try {
                file.createNewFile();
                final String abspath = file.getAbsolutePath();
                Runtime.getRuntime().exec("chmod 777 " + abspath).waitFor();
                final OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
                if (new File("/system/bin/sh").exists()) {
                    out.write("#!/system/bin/sh\n");
                }
                out.write(script);
                if (!script.endsWith("\n")) {
                    out.write("\n");
                }
                out.write("exit\n");
                out.flush();
                out.close();

                exec = Runtime.getRuntime().exec("su");
                DataOutputStream os = new DataOutputStream(exec.getOutputStream());
                os.writeBytes(abspath);
                os.flush();
                os.close();

                InputStreamReader r = new InputStreamReader(exec.getInputStream());
                final char buf[] = new char[1024];
                int read = 0;
                while ((read = r.read(buf)) != -1) {
                    if (res != null) {
                        res.append(buf, 0, read);
                    }
                }

                r = new InputStreamReader(exec.getErrorStream());
                read = 0;
                while ((read = r.read(buf)) != -1) {
                    if (res != null) {
                        res.append(buf, 0, read);
                    }
                }

                if (exec != null) {
                    this.exitcode = exec.waitFor();
                }
            } catch (InterruptedException ex) {
                if (res != null) {
                    res.append("\nOperation timed-out");
                }
            } catch (Exception ex) {
                if (res != null) {
                    res.append("\n" + ex);
                }
            } finally {
                destroy();
            }
        }

        public synchronized void destroy() {
            if (exec != null) {
                exec.destroy();
            }
            exec = null;
        }
    }

}
