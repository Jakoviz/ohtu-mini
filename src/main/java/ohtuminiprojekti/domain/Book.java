package ohtuminiprojekti.domain;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Bookmark {

  private String type = "book";
  private String title;
  private String author;

}
