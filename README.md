# Java-demo-app

### Create
```
$> curl -i --header "Content-Type: application/json" \
        --request POST \
        --data '{"name": "Restaurant name","description":"Restaurant description"}' \
        --url http://<host>:8081/restaurants
```

### Read
```
$> curl -i --request GET --url http://<host>:8081/restaurants

$> curl -i --request GET --url http://<host>:8081/restaurants/{uuid}

$> curl -i --request GET --url http://<host>:8081/restaurants/?name=foo
```

### Update
```
$> curl -i --header "Content-Type: application/json" \
    --request PATCH \
    --data '{"name": "Another restaurant Name","description":"Another restaurant description"}' \
    --url http://<host>:8081/restaurants/{uuid}
```

### Delete
```
$> curl --request DELETE --url http://<host>:8081/restaurants/{uuid}
```	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 


	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

	 

