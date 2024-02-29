package et.com.commomHelper.repos;

import et.com.commomHelper.domain.Configuration;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepos extends CrudRepository<Configuration, Long> {
}
