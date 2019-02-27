package kurio_tetsuya.todo.ui.view;

import java.util.List;

import kurio_tetsuya.todo.model.TaskModel;

public interface IMainView {

        void showProgress();
        void hideProgress();
        void showList(List<TaskModel> list);
        void errorList();
}
