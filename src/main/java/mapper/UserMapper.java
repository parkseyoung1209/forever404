package mapper;

import org.apache.ibatis.annotations.Mapper;

import com.semi.forever404.model.vo.User;

@Mapper
public interface UserMapper {
	
	void register(User user);
}
