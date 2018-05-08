package com.wkw.archives.domain.interactor;

import com.vongihealth.network.executor.PostExecutionThread;
import com.vongihealth.network.executor.ThreadExecutor;
import com.vongihealth.network.interactor.UseCase;
import com.wkw.archives.domain.repository.ArchivesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by wukewei on 2017/9/12.
 */

public class PasswordUseCase extends UseCase<String, Void> {


    private final ArchivesRepository mArchivesRepository;

    @Inject
    public PasswordUseCase(ArchivesRepository archivesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mArchivesRepository = archivesRepository;

    }

    @Override
    public Observable<String> buildUseCaseObservable(Void params) {
        return mArchivesRepository.fetchPassword();
    }


}
