package mapper;

import org.apache.ibatis.annotations.Mapper;

import com.semi.forever404.model.vo.ServiceInfo;

@Mapper
public interface ServiceMapper {
	void addInfo(ServiceInfo si);
}
