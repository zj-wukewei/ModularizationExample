package com.wkw.archives.domain.interactor;

import com.wkw.archives.domain.entity.ArchivesEntity;
import com.wkw.archives.domain.repository.ArchivesRepository;
import com.wkw.basic.executor.PostExecutionThread;
import com.wkw.basic.executor.ThreadExecutor;
import com.wkw.basic.interactor.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by wukewei on 2017/9/12.
 */

public class ArchivesListUseCase extends UseCase<ArchivesEntity, ArchivesListUseCase.Params> {


    private final ArchivesRepository mArchivesRepository;

    @Inject
    public ArchivesListUseCase(ArchivesRepository archivesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mArchivesRepository = archivesRepository;

    }

    @Override
    public Observable<ArchivesEntity> buildUseCaseObservable(Params params) {
        return mArchivesRepository.archivesList();
    }

    public static final class Params {
        private final int pn;

        public Params(int pn) {
            this.pn = pn;
        }

        public static Params forArchives(int pn) {
            return new Params(pn);
        }
    }
}
