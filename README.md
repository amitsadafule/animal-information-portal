# animal-information-portal

APIs
- GET /api/v1/animals : Search animals
    - Request Params:
        - type: Optional, Min 3 characters
        - breed: Optional, Min 3 characters
        - If nothing given return all animals
    - Response: application/json
        - If animals are found 
            ```
            HTTP Status 200
            {
            	animals: [{
            		id: “”,
            		type: “”,
            		breed: “”,
            		name: “”,
            		profilePicture: “”,
            }]
            }    
            ```
        - If no animal information found 
          ```
          HTTP status code 200
          {
          	animals: []
          }
          ```
          
        - If any parameter passed is not as per standard
          ```
          HTTP status code 400
          {
          	code: “”,
          	description: “”
          }
          ```
          
- GET /api/v1/animals/{animal_id} : Get single animal information
    - Response : application/json
        - If Animal is found
            ```
             HTTP status code 200
             {
                id: “”,
                type: “”,
                breed: “”,
                name: “”,
                profilePicture: “”,
                description: “”
                features: [{
                        name: “”,
                        value: “”
                    }]
             }
            ```
        - If no animal found by given animal_id 
          ```
          HTTP status code 404
          {
          	code: “”,
          	description: “”
          }
          ```




