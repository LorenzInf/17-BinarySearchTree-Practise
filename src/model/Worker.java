package model;

//TODO 01: Setze das Interface um. Finde heraus, was der Suchschlüssel eines jeden Worker-Objekts ist.
public class Worker implements ComparableContent<Worker> {
    private String name;
    private Queue<Task> allTasks;

    public Worker(String name){
        this.name = name;
        allTasks = new Queue<>();
    }

    public String getName(){
        return name;
    }

    public void addTask(int id){
        allTasks.enqueue(new Task(id));
    }

    public Task completeTask(){
        Task t = allTasks.front();
        allTasks.dequeue();
        return t;
    }

    @Override
    public boolean isGreater(Worker pContent) {
        return name.compareTo(pContent.getName()) > 0;
    }

    @Override
    public boolean isEqual(Worker pContent) {
        return name.compareTo(pContent.getName()) == 0;
    }

    @Override
    public boolean isLess(Worker pContent) {
        return name.compareTo(pContent.getName()) < 0;
    }
}
