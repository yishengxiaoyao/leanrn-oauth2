TOKEN=`curl -X POST --user clientapp:123456 http://localhost:8080/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "grant_type=client_credentials&scope=READ_USER_INFO" | jq '.access_token'|sed 's/\"//g'`
echo "tGot token for curl client as :$TOKEN"
curl -X GET http://localhost:8080/api/userInfo -H "Authorization: Bearer $TOKEN"
#should install jq
#brew install jq
# result {"name":"xiaoyao","email":"xiaoyao@test.com"}