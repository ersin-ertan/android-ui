package com.nullcognition.joebirchmvvm.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.text.Html;


import com.nullcognition.joebirchmvvm.R;
import com.nullcognition.joebirchmvvm.model.Post;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

// because the view model can be composed of multiple models, we need to model the view
// and accept models as, part of the constructor, this could also be a good place to save
// view state if the context is passed in

public class CommentHeaderViewModel extends BaseObservable {

    private Context context;
    private Post    post;

    public CommentHeaderViewModel(Context context, Post post){
        this.context = context;
        this.post = post;
    }

    public String getCommentText(){
        return Html.fromHtml(post.text.trim()).toString();
    }

    public String getCommentAuthor(){
        return context.getResources().getString(R.string.text_comment_author, post.by);
    }

    public String getCommentDate() {
        return new PrettyTime().format(new Date(post.time * 1000));
    }

}
