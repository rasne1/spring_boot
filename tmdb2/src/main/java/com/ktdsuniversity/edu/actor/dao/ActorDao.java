package com.ktdsuniversity.edu.actor.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.actor.vo.request.ActorWriteVO;

@Mapper
public interface ActorDao {

	int insertNewActor(ActorWriteVO actorWriteVO);

}
