'use strict';


App.controller('PostsController', ['$scope', 'PostsService', function($scope, PostsService) {
    var self = this;
    self.postEntity={id:null,author:'',title:'',content:'',date:''};
    self.postList=[];


    self.fetchAllPosts = function(){
        PostsService.fetchAllPosts()
            .then(
                function(d) {
                    self.postList = d;
                },
                function(errResponse){
                    console.error('Error while fetching posts');
                }
            );
    };

    self.createPost = function(postEntity){
        PostsService.createPost(postEntity)
            .then(
                self.fetchAllPosts,
                function(errResponse){
                    console.error('Error while creating post.');
                }
            );
    };

    self.updatePost = function(postEntity, id){
        PostsService.updatePost(postEntity, id)
            .then(
                self.fetchAllPosts,
                function(errResponse){
                    console.error('Error while updating post.');
                }
            );
    };

    self.deletePost = function(id){
        PostsService.deletePost(id)
            .then(
                self.fetchAllPosts,
                function(errResponse){
                    console.error('Error while deleting post.');
                }
            );
    };

    self.fetchAllPosts();

    self.submit = function() {
        if(self.postEntity.id===null){
            console.log('Saving new post', self.postEntity);
            self.createPost(self.postEntity);
        }else{
            self.updatePost(self.postEntity, self.postEntity.id);
            console.log('Post updated with id ', self.postEntity.id);
        }
        self.reset();
    };

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.postList.length; i++){
            if(self.postList[i].id === id) {
                self.postEntity = angular.copy(self.postList[i]);
                break;
            }
        }
    }

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.postEntity.id === id) {//clean the form if the postEntity to be deleted is shown there.
            self.reset();
        }
        self.deletePost(id);
    }

    self.reset = function(){
        self.postEntity={id:null,author:'',title:'',content:'',date:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}])
    .config(['$routeProvider','$locationProvider',function($routeProvider,$locationProvider){
        $routeProvider.when("/post/:postId",
            {
                template: "/post",
                controller: "SpecificPostController"
            }
        );
        $locationProvider.html5Mode(true);

    }])

    .controller('SpecificPostController', function($scope, $routeParams) {
        var postId = $routeParams.postId;
        $scope.postEntity={};
        $scope.postEntity.id = postId;
    });

