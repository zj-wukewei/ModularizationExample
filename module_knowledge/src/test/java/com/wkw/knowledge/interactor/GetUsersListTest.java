package com.wkw.knowledge.interactor;

import com.vongihealth.network.executor.PostExecutionThread;
import com.vongihealth.network.executor.ThreadExecutor;
import com.wkw.commonbusiness.entity.AbstractQry;
import com.wkw.knowledge.domain.UsersListUseCase;
import com.wkw.knowledge.domain.repository.KnowledgeRepository;
import com.wkw.knowledge.entity.User;
import com.wkw.uiframework.base.mvp.page.PageEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersListTest {
    private static final int PN = 1;
    private UsersListUseCase getUsersList;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private KnowledgeRepository mockKnowledgeRepository;

    @Before
    public void setUp() {
        getUsersList = new UsersListUseCase(mockKnowledgeRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetUserListUseCaseObservableHappyCase() {
        AbstractQry qry = UsersListUseCase.create(PN);
        getUsersList.buildUseCaseObservable(qry);

        verify(mockKnowledgeRepository).users(qry);
        verifyNoMoreInteractions(mockKnowledgeRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
