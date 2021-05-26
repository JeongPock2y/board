package com.victolee.board.domain.repository;

import com.victolee.board.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByTitleContaining(String keyword);
    //method 이름과 By뒤에 구하고자하는 키워드  + Containing 을 붙히면  
    //마치 Like문을 쓴것처럼 사용 가능  여기서는 %{keyword}%  결국 이걸 저렇게 표현한것
}
