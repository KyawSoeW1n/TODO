package kurio_tetsuya.todo.View;

import java.util.List;

import kurio_tetsuya.todo.Model.TaskModel;

public interface IMainView {

        public void showProgress();
        public void hideProgress();
        public void showList(List<TaskModel> list);
        public void errorList();
}
