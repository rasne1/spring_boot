package com.ktdsuniversity.edu.replies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.replies.dao.RepliesDao;

@Service
public class RepliesServiceImpl implements RepliesService {
	
	@Autowired
	private RepliesDao repliesDao;

}
