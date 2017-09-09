package com.wkw.basic.cache;

import android.content.Context;

import com.google.gson.Gson;
import com.wkw.basic.exception.UserNotFoundException;
import com.wkw.basic.executor.ThreadExecutor;
import com.wkw.basic.model.UserEntity;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by wukewei on 2017/9/2.
 */
@Singleton
public class UserCacheImpl implements UserCache {


    private final Context context;
    private final File cacheDir;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;
    private static final String DEFAULT_FILE_NAME = "user_";
    private final Gson mGson = new Gson();

    @Inject
    UserCacheImpl(Context context,
                  FileManager fileManager, ThreadExecutor executor) {
        if (context == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public Observable<UserEntity> get(int userId) {
        return Observable.create(emitter -> {
            final File userEntityFile = UserCacheImpl.this.buildFile(userId);
            final String fileContent = UserCacheImpl.this.fileManager.readFileContent(userEntityFile);
            final UserEntity userEntity =
                    UserCacheImpl.this.mGson.fromJson(fileContent, UserEntity.class);

            if (userEntity != null) {
                emitter.onNext(userEntity);
                emitter.onComplete();
            } else {
                emitter.onError(new UserNotFoundException());
            }
        });
    }

    @Override
    public void put(UserEntity userEntity) {
        if (userEntity != null) {
            final File userEntityFile = this.buildFile(userEntity.getUserId());
            final String jsonString = this.mGson.toJson(userEntity, UserEntity.class);
            this.executeAsynchronously(new CacheWriter(this.fileManager, userEntityFile, jsonString));
        }
    }


    private File buildFile(int userId) {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(userId);

        return new File(fileNameBuilder.toString());
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }

}
