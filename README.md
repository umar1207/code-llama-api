### api for code-llama code reviewer
#### leverages together ai

#### using curl
``` bash
curl -X POST -H "Content-Type: application/json" -d '{
  "code" : git diff goes here
}' http://localhost:8080/api/review

```

#### set your together ai api key