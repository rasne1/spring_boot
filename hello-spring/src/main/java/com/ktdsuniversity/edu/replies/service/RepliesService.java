package com.ktdsuniversity.edu.replies.service;

import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;
import com.ktdsuniversity.edu.replies.vo.response.DeleteResultVO;
import com.ktdsuniversity.edu.replies.vo.response.RecommendResultVO;
import com.ktdsuniversity.edu.replies.vo.response.SearchResultVO;

public interface RepliesService {

	RepliesVO createNewReply(CreateVO createVO);

	SearchResultVO findRepliesByArticleId(String articleId);

	RepliesVO findReplyByReplyId(String replyId);

	RecommendResultVO updateRecommendByReplyId(String replyId);
	
	DeleteResultVO deleteReplyByReplyId(String replyId);

}
