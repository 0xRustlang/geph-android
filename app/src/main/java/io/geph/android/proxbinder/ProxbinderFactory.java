package io.geph.android.proxbinder;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import io.geph.android.api.GephService;
import io.geph.android.api.GephServiceFactory;

/**
 * @author j3sawyer
 */
public class ProxbinderFactory {
    public static final String TAG = ProxbinderFactory.class.getSimpleName();
    private static AtomicInteger counter = new AtomicInteger();

    public static Proxbinder getProxbinder(Context context) {
        final String daemonBinaryPath =
                context.getApplicationInfo().nativeLibraryDir + "/libgeph.so";
        ProcessBuilder pb = new ProcessBuilder(daemonBinaryPath, "proxbinder");
        final Process proc;
        try {
            proc = pb.start();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        String tempProxBinderDest = "";
        Scanner read = new Scanner(proc.getInputStream());
        while (read.hasNextLine()) {
            tempProxBinderDest = read.nextLine();
            if (!tempProxBinderDest.isEmpty()) break;
        }
        read.close();

        GephService service = GephServiceFactory.getService("http://" + tempProxBinderDest);

        return new Proxbinder(counter.incrementAndGet(), proc, service);
    }
}
