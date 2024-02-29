package et.com.commomHelper.domain;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class  Configuration
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String key;
    private String value;


}
