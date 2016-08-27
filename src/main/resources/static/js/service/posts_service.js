'use strict';

App.factory('PostsService', ['$http', '$q', function($http, $q){
    var REST_SERVICE_POST_URI = 'http://localhost:8080/rest/posts/';
    return {

        fetchAllPosts: function() {
            return $http.get(REST_SERVICE_POST_URI)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching posts');
                        return $q.reject(errResponse);
                    }
                );
        },

        fetchSpecificPost: function(id) {
            return $http.get(REST_SERVICE_POST_URI+id)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching specific post');
                        return $q.reject(errResponse);
                    }
                );
        },

        createPost: function(postEntity){
            return $http.post(REST_SERVICE_POST_URI, postEntity)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while creating post');
                        return $q.reject(errResponse);
                    }
                );
        },

        updatePost: function(postEntity, id){
            return $http.put(REST_SERVICE_POST_URI+id, postEntity)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while updating post');
                        return $q.reject(errResponse);
                    }
                );
        },

        deletePost: function(id){
            return $http.delete(REST_SERVICE_POST_URI+id)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while deleting post');
                        return $q.reject(errResponse);
                    }
                );
        }

    };

}]);
