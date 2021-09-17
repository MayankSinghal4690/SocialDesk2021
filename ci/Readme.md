# Apps Deployed and Endpoints
## There are 2 Services deployed for frontend and backend
##### Spring Backend Service
- Name: spring
- Endpoint: https://spring-dot-grads-coding-group-15.uc.r.appspot.com
- Instance Type: F2
- Responsible Cloud Build Triggers:
-- [BackendOnly](https://console.cloud.google.com/cloud-build/triggers/edit/41603811-3dde-48e7-8ba1-78ba92508aec?cloudshell=false&project=grads-coding-group-15)
-- [Deploy-final-backend](https://console.cloud.google.com/cloud-build/triggers/edit/55b23d00-20ac-40c7-8c1a-0c49b6fc6ad7?cloudshell=false&project=grads-coding-group-15)

##### React Frontend Service
- Name: default
- Endpoint: https://grads-coding-group-15.uc.r.appspot.com/
- Instance Type: F1
- Responsible Cloud Build Triggers:
-- [FrontedOnly](https://console.cloud.google.com/cloud-build/triggers/edit/3de19580-495a-4d8c-97e5-d403a85ba92d?cloudshell=false&project=grads-coding-group-15)
-- [Deploy-final-frontend](https://console.cloud.google.com/cloud-build/triggers/edit/98575f8d-a9d7-4a2b-b2a8-139ac8248683?cloudshell=false&project=grads-coding-group-15)




# CI/CD pipelines deployed
## There are total of 3 ways to deploy app
##### Deploy complete app
- If you want to deploy the complete app, make sure the app builds and push to the "deploy" branch only
- It will trigger the pipelines for both frontend and backend apps
- ```git push origin:deploy```
##### Deploy Backend only
- If you want to check any specific feature of backend app, deploy it to a branch with branch name starting with "deploy-backend"
- Eg: ```git push origin:deploy-backend-feature1```
##### Deploy Frontend only
- If you want to check any specific feature of frontend app, deploy it to a branch with branch name starting with "deploy-frontend"
- Eg: ```git push origin:deploy-frontend-newStyling1```