<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<div class="container">
	<div class="row">
		
		<c:if test="${not empty message}">
		
		<div class="col-xs-12">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="close" data-dismiss="alert" >&times;</button>
				${message}
			</div> 
		</div>
			
		</c:if>
	
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>

				<div class="panel-body">
					
					<!-- enctype="multipart/form-data" to tell the spring form that the file type is MultipartFile -->
					<sf:form class="form-horizontal" modelAttribute="product" action="${contextRoot}/manage/products" method="POST" enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter Product Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" placeholder="Product Name" class="form-control"/>
								<!-- <em class="help-block">Please enter Product Name!</em> -->
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Enter Brand Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control"/>
								<!-- <em class="help-block">Please enter Brand Name!</em> -->
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product Description: </label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4" placeholder="Write a description!" class="form-control" />
								<!-- <em class="help-block">Please enter a description!</em> -->
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Enter Unit Price: </label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice" placeholder="Unit Price in &#8377" class="form-control"/>
								<!-- <em class="help-block">Please enter Brand Name!</em> -->
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity Available: </label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" placeholder="Quantity Available!" class="form-control"/>
								<!-- <em class="help-block">Please enter Brand Name!</em> -->
								<sf:errors path="quantity" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="file">Select and image: </label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control"/>
								<!-- <em class="help-block">Please enter Brand Name!</em> -->
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select Category: </label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId" path="categoryId" items="${categories}" itemLabel="name" itemValue="id"/>
								<!-- <em class="help-block">Please enter Brand Name!</em> -->
								<c:if test="${product.id == 0}">
									<div class="text-right" >
										<br/>
										<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs" >Add Category</button>
									</div>
								</c:if>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit" class="form-control btn btn-primary">
								
								<!-- Other products fields (Hidden) -->
								<sf:hidden path="id"/>
								<sf:hidden path="supplierId"/>
								<sf:hidden path="active"/>
								<sf:hidden path="purchases"/>
								<sf:hidden path="views"/>
								<sf:hidden path="code"/>
								
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	<h1>Available Products</h1>
	<hr/>
	<div class="row">
		<div class="col-xs-12">
				<table id="adminProductsTable" class="table table-striped table-bordered" >
					<thead>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Brand</th>
							<th>Name</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</thead>
					
					<tfoot>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</tfoot>
				</table>
		</div>
		
	</div>
	
	<div class="modal" tabindex="-1" role="dialog" id="myCategoryModal">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Add new Category</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <sf:form modelAttribute="category" action="${contextRoot}/manage/category" method="POST" class="form-horizontal">
        	<div class="form-group">
        		<label for="category_name" class="col-md-4">Name</label>
        		<div class="col-md-8">
        			<sf:input path="name" id="category_name" type="text" class="form-control"/>
        		</div>
        	</div>
        	
        	<div class="form-group">
        		<label for="category_description" class="col-md-4">Description</label>
        		<div class="col-md-8">
        			<sf:textarea path="description" id="category_description" rows="5" class="form-control"/>
        		</div>
        	</div>
        	
        	<div class="form-group">
        		<div class="col-md-offset-4 col-md-8">
        			<input type="submit" value="Add Category" class="btn btn-primary" />
        		</div>
        	</div>
        </sf:form>
      </div>
    </div>
  </div>
</div>
</div>