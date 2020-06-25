//We are using $http and $scope bultin angular service
//$http service is a function which takes a single argument — a configuration object — that is used to generate an HTTP request and returns a promise that is resolved (request success) or rejected (request failure) with a response object.
//$http service has methods like GET,POST,PUT,DELETE et. 
//$scope The scope is a JavaScript object with properties and methods, which are available for both the view and the controller.
var cartApp=angular.module("cartApp",[]);
cartApp.controller("cartCtrl",function($scope,$http,$log){
	
	$scope.addCartItem=function(productId){
		console.log("Hello from Angular+" + productId);
		let priceValue=parseFloat($('#priceValue').val());
		let quantity =parseInt($('#quantity').val());

		
		// price hidden we are getting price from server and assign it to a
		// price hidden. Increment price value and add it to original price
		priceValue+= parseFloat($('#priceHidden').val());
		quantity+=1;
		// quantity is hidden and it is sent to server
		$('#quantity').val(quantity);
		$('#priceValue').val(priceValue.toFixed(2));
		// Total is shown to user on product page
		$('#total').html(quantity);
		//Shpping cart incease by one and show on the navbar
		// success callback function will be called if put method exec.
		// successfully
		$http.put('/cart/add/'+productId).success(function(data){
			// We can use another built in angular service which is $log, it has
			// info method which can log the passed object to a console
			// lets try log methods, but first inject $log service to the
			// angular controller
			$scope.refreshCart($http.get('/CraftShop/rest/cart/cartId/'));
			alertify.success("Added to the cart!")
			$log.info(data);
			// $scope.refreshCart($http.get('/CraftShop/rest/cart/cartId/'));
			// alert("Product successfully added to the cart!")
		});

	};	
	$scope.removeCartItem=function(productId){
		
		let priceValue=parseFloat($('#priceValue').val());
		let quantity =parseInt($('#quantity').val());

		// To not go in minus
		// Validation is saying if quantity is one we wanna keep it as original
		// price no matter what
		if(quantity==0){
			priceValue= 0
			// the quantity will always be one
			quantity=0;
		
		}else{
			priceValue-=parseFloat($('#priceHidden').val());
			quantity-=1;
			
			// While clicking on - button we want to remove cart item, last
			// click we want to delete cart
			//if(quantity>0){
			$http.put('/cart/remove/'+productId).success(function(data){
				$scope.refreshCart($http.get('/CraftShop/rest/cart/cartId/'));
				alertify.success("Removed from cart!")
				
				// var notification = alertify.notify('sample', 'success', 5,
				// function(){ console.log('dismissed'); });
			});
			// Delete cart when quantity == 0
		//}else{
			//$http.delete('/cart/deleteCart').success(function(data){
				//alertify.success("Cart cleared!");
				//$scope.refreshCart('5ed7808076419e1361ade789');
				
			//});
			
		
		//}
		}

		// quantity is hidden and it is sent to server
		$('#quantity').val(quantity);
		$('#priceValue').val(priceValue.toFixed(2));
		// Total is shown to user
		$('#total').html(quantity);

	};
	
	
	
	$scope.initCartId=function(cartId){
		
		$scope.cartId=cartId;
		$scope.refreshCart(cartId);
		
		
	};
	
	$scope.refreshCart=function (cartId){
		console.log("cartid JE =" +cartId);
		$http.get('/rest/cart/'+$scope.cartId).success(function (data){
			console.log("Hello From refresh" + data);
			$scope.cart=data;
			$scope.$apply();
		});
	};
	

	
});
// $scope.clearCart=function(){
// $http.delete('/cart/deleteCart').success(function(data){
// alertify.success("Cart cleared!")
// };
//$scope.clearCart=function(){
//	console.log("Hello from clearCart+");
//	$http.delete('/cart/deleteCart').success(function(data){
//		alertify.success("Cart cleared!")
//	});
//};

