// You actually apply event driven method, and whenever you click something which is plus in our case,than do something
$(document).on('click','#plus',function(url){
	
     
	e.preventDefault();
	//We are parsing value of the text so we can do some calculation with numbers
	let priceValue=parseFloat($('#priceValue').val());
	let quantity =parseInt($('#quantity').val());
	//price hidden we are getting price from server and assign it to a price hidden. Increment price value and add it to original price
	priceValue+= parseFloat($('#priceHidden').val());
	quantity+=1;
	
	//quantity is hidden and it is sent to server
	$('#quantity').val(quantity);
	$('#priceValue').val(priceValue.toFixed(2));
	//Total is shown to user
	$('#total').html(quantity);
	
});

$(document).on('click', '#minus',function(e){
	
	e.preventDefault();
	let priceValue=parseFloat($('#priceValue').val());
	let quantity =parseInt($('#quantity').val());

	//To not go in minus
	//Validation is saying if quantity is one we wanna keep it as original price no matter what
	if(quantity==1){
		priceValue= parseFloat($('#priceHidden').val());
		//the quantity will always be one
		quantity=1;
	}else{
		priceValue-=parseFloat($('#priceHidden').val());
		quantity-=1;
	}
	//quantity is hidden and it is sent to server
	$('#quantity').val(quantity);
	$('#priceValue').val(priceValue.toFixed(2));
	//Total is shown to user
	$('#total').html(quantity);

})

