import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

public class DeleteTasksDto {
    
    private Long id;
    private String taskName;
    private int categoryId;
    private String deadline;
    private boolean makeArchived;

    public DeleteTasksDto(Long id, String taskName, int categoryId, String deadline, boolean makeArchived){
        this.id = id;
        this.taskName = taskName;
        this.categoryId = categoryId;
        this.deadline = deadline;
        this.makeArchived = makeArchived;
    }
    
    public Long getId(){
       return id; 
    }

    public void setId(Long id){
      this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setName(String taskName) {
        this.taskName = taskName;
    }
    
    public Integer getCategoryId(){
       return categoryId; 
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline=deadline;
    }

    public boolean getMakeArchived() {
        return makeArchived;
    }

    public void setMakeArchived(boolean makeArchived) {
        this.makeArchived = makeArchived;
    }


}

