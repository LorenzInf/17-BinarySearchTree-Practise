package control;

import model.BinarySearchTree;
import model.Queue;
import model.Task;
import model.Worker;

public class WorkerHandler {

    private BinarySearchTree<Worker> allWorker;
    int workerCount = 1;
    int idCount = 1;

    public WorkerHandler(){
        allWorker = new BinarySearchTree<>();
    }

    /**
     * Diese Methode fügt einen Auftrag mit entsprechender id zum entsprechenden Arbeiter in die Warteschlange hinzu.
     * Falls der Arbeiter nicht existiert, so wird er zunächst erstellt und dem Baum hinzugefügt.
     * @param name
     * @param id
     */
    public void addTaskAndWorker(String name, int id){
        //TODO 03: Setzen Sie die Methode gemäß obiger Beschreibung um.
        Worker worker = new Worker(name);
        if(allWorker.search(worker) == null) {
            allWorker.insert(worker);
        }
        allWorker.search(worker).addTask(id);
    }

    /**
     * Diese Methode entfernt alle Arbeitsaufträge aus dem Baum.
     * Dabei werden alle Arbeitsaufträge sortiert nach ihrem Arbeiter als großer, vollständiger String in der Systemkonsole ausgegeben.
     * Nach Aufruf dieser Methode befinden sich alle Arbeiter immer noch im Baum, jedoch hat keiner mehr einen Arbeitsauftrag.
     */
    public void releaseAllTasksAndShowWorker(){
        System.out.println(releaseAllTasksAndShowWorker(allWorker));
        workerCount = 1;
    }

    /**
     * Rekursive Methode, die am Ende einen String liefert. Dieser hat folgenden Aufbau: 1.Name:1.id-2.id-3.id#2.Name:1.id-2.id#3.Name:1.id etc.
     * Die Namen sind alphabetisch sortiert.
     * Nach Aufruf dieser Methode befindet sich kein Auftrag mehr im Baum. Die Arbeiter werden nicht gelöscht.
     * @param tree
     * @return String bestehend aus Arbeiternamen und deren IDs.
     */
    private String releaseAllTasksAndShowWorker(BinarySearchTree<Worker> tree){
        StringBuilder output = new StringBuilder();
        if(!tree.getLeftTree().isEmpty()) {
            output.append(releaseAllTasksAndShowWorker(tree.getLeftTree()));
        }

        output.append(workerCount).append(".").append( tree.getContent().getName() ).append(":");
        workerCount++;

        while(tree.getContent().getCurrentTask() != null) {
            output.append(idCount).append(".").append( tree.getContent().completeTask().getID() );
            idCount++;
            if(tree.getContent().getCurrentTask() != null) {
                output.append("-");
            }
        }
        idCount = 1;

        output.append("#");

        if(!tree.getRightTree().isEmpty()) {
            output.append(releaseAllTasksAndShowWorker(tree.getRightTree()));
        }
        //TODO 04a: Stellen Sie handschriftlich die gewünschte Ausgabe gemäß des vorhanden Baums dar (siehe MainController ab Zeile 13). Hierbei genügen die ersten drei Arbeiter und ihre IDs, die von dieser Methode ausgegeben werden.
        //TODO 04b: Setzen Sie anschließend diese Methode gemäß obiger Beschreibung um.
        return output.toString();
    }

    public void releaseAllTasksAndShowWorkerButGood(){
        System.out.println(releaseAllTasksAndShowWorkerButGood(allWorker));
    }

    /**
     * Same thing as above, but it's actually good
     */
    private String releaseAllTasksAndShowWorkerButGood(BinarySearchTree<Worker> tree){
        StringBuilder output = new StringBuilder();
        if(!tree.getLeftTree().isEmpty()) {
            output.append(releaseAllTasksAndShowWorkerButGood(tree.getLeftTree()));
        }

        output.append("Worker Name: ").append( tree.getContent().getName() ).append("\nTasks:\n");

        while(tree.getContent().getCurrentTask() != null) {
            output.append(idCount).append(". ID: ").append( tree.getContent().completeTask().getID() ).append("\n");
            idCount++;
        }
        idCount = 1;

        output.append("\n");

        if(!tree.getRightTree().isEmpty()) {
            output.append(releaseAllTasksAndShowWorkerButGood(tree.getRightTree()));
        }
        return output.toString();
    }
    /*private String releaseAllTasksAndShowWorkerButGood(BinarySearchTree<Worker> tree){
        StringBuilder output = new StringBuilder();
        if(!tree.getLeftTree().isEmpty()) {
            output.append(releaseAllTasksAndShowWorkerButGood(tree.getLeftTree()));
        }

        output.append(tree.getContent().getName()).append(" ");

        if(!tree.getRightTree().isEmpty()) {
            output.append(releaseAllTasksAndShowWorkerButGood(tree.getRightTree()));
        }
        return output.toString();
    }*/
}
