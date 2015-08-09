<%--
	- page-template.jsp
	- A template page for your reference
	-
	- @author - Your name here
--%>
<%@ page contentType="text/html;utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tides" uri="http://www.ideyatech.com/tides"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>

<app:header pageTitle="label.expense" active="expense"/>

<div id="expense-body">

<div id="search-body">

	<div id="search-panel" class="span3">

		<div id="search-panel-inner" data-spy="affix" data-offset-top="60">
			<div class="navbar">
				<div class="navbar-inner">
					<span class="brand"><i class="icon-search"></i><spring:message code="label.search" /></span>
					<a class="show-search-form btn collapsed pull-right hidden-desktop hidden-tablet" data-toggle="collapse" data-target=".search-form">
						<i class="icon-chevron-up"></i>
						<i class="icon-chevron-down"></i>
					</a>
				</div>
			</div>
			<div class="search-form collapse">
				<form:form modelAttribute="searchCommand" id="expense-search" >
					<!-- Define search fields here -->
					<tides:date_picker path="date" label="expense.date"/>
					<hr/>
					<input type="submit" class="btn btn-info btn-block" data-submit="search" value="<spring:message code="label.search"/>">
					<button type="button" class="btn btn-link btn-block" data-submit="clear"><spring:message code="label.clear" /></button>
				</form:form>
			</div>
		</div>
	</div>
	
	<div id="results-panel" class="span9">
		
		<div id="message-panel" class="row-fluid">
			<button id="expense-add" class="btn btn-info add-action">
				<i class="icon-plus-sign icon-white"></i>
				<spring:message code="label.expense.add" />
			</button>
			<button id="generateReportBtn" class="btn btn-info">
				Generate Report
			</button>
			<div class="status" data-display-pagelinks="false" data-display-summary="true" data-summary-message='
				<spring:message code="message.displaying-x-of-y" arguments="#start,#end,#total,records"/>
			'>
				<tides:status results="${results}" />
			</div>
		</div>
		
		<div class="clear"></div>
		
		<div class="table-wrapper-2 overflow-hidden">
			<div class="table-wrapper">
				<table id="expense-results" class="footable table-bordered table-striped table-hover table-condensed" data-page="${results.currPage}" >
					<thead>
						<tr class="table-header">
							<!-- Define headers here -->
							<th>Date</th>
							<th>Receipt</th>
							<th>Amount</th>
							<th>Type</th>
							<th data-field-name="ot3-controls"></th>
						</tr>
					</thead>
					<tbody>
						<script type="text/template" class="template">
	                		<tr id="expense-row-{{id}}" data-id="{{id}}">
								<!-- Define template here -->
								<td>{{date}}</td>
								<td>{{receiptView}}</td>
								<td>{{amount}}</td>
								<td>{{type}}</td>
								<td>
									<i class='icon-pencil edit-action' data-id='{{id}}' data-title="<spring:message code="label.edit" />"></i>
									<i class='icon-trash remove-action' data-id='{{id}}' data-title="<spring:message code="label.delete" />"></i>
								</td>
							</tr>
						</script>
						<c:forEach items="${results.results}" var="record" varStatus="status">
							<tr data-id="${record.id}">
								<!-- Define table body here -->
								<td>${record.date}</td>
								<td>${record.receiptView}</td>
								<td>${record.amount}</td>
								<td>${record.type}</td>
								<td>
									<i class='icon-pencil edit-action' data-id='${record.id}' data-title="<spring:message code="label.edit" />"></i>
									<i class='icon-trash remove-action' data-id='${record.id}' data-title="<spring:message code="label.delete" />"></i>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	
			</div>
		</div>

		<div class="paging clearfix">
			<tides:paging results="${results}"/>
		</div>
	</div>
	
</div>

<div id="form-body" class="modal fade hide">

	<div id="form-panel" >
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="${add}"><spring:message code="label.expense.add" /></h4>
			<h4 class="${update}"><spring:message code="label.expense.update" /></h4>
		</div>

		<form:form modelAttribute="formCommand" id="expense-form">
			<div class="modal-body">
				<div class="message-container"></div>
				<form:hidden path="receipt" />
				<tides:date_picker path="date" label="expense.date"/>
				<tides:input path="tempReceipt.tin" label="receipt.tin"/>
				<div id="tinSelect2" class="select2-container select2-container-multi combobox" style="width: 220px; display: none;">
				<ul class="select2-choices">
					<li class="select2-search-choice">
						<div></div>
						<a href="#" class="select2-search-choice-close"></a>
					</li>
				</ul>
				</div>
				<tides:input path="tempReceipt.name" label="receipt.name"/>
				<tides:input path="tempReceipt.address1" label="receipt.address1"/>
				<tides:input path="tempReceipt.address2" label="receipt.address2"/>
				<tides:input path="amount" label="expense.amount"/>
				<tides:input path="lessAmount" label="expense.lessAmount"/>
				<tides:select path="type" label="expense.type" items="${expenseTypes}"/>
				<!-- Define form fields here -->
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-link" data-dismiss="modal"><spring:message code="label.close" /></button>
				<input type="submit" class="btn btn-info  ${add}" data-form-display="add" data-submit="save-and-new" value="<spring:message code="label.save-and-new" />" />
				<input type="submit" class="btn btn-success" data-submit="save" value="<spring:message code="label.save" />" />
				<input type="hidden" name="id" />
			</div>
		</form:form>
	</div>
	
</div>

</div>

<div id="generateModal" class="modal hide fade" role="dialog" data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
	Generate Expense Report
    </div>
	<div class="modal-body">
		<div class="control-group">
			<label class="control-label">Month/Year</label>
			<div class="controls">
				<div id="monthYearDiv" class="date input-append">
					<input id="monthYear" id="ot-datepicker" />
					<span class="add-on"><i class="icon-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&nbsp;</label>
			<button id="actualGenerate" class="btn btn-info">
				Generate Report
			</button>
			<button id="cancelGenerate" class="btn" >
				Cancel
			</button>
		</div>
	</div>
	<div class="modal-footer">	
	</div>
</div>

<tides:footer>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#expense-body").RESTful();
			$('body').tooltip({selector: '.edit-action, .remove-action'});
			
			var $target = $('input[id="monthYear"]');
			$target.datepicker({
				format: "mm/dd/yyyy",
				autoclose : true,
				todayBtn : true
			});
			
			$('#generateReportBtn').click(function() {
				$('#generateModal').modal('show');
			});
			
			$('#cancelGenerate').click(function() {
				$('#generateModal').modal('hide');
			});
			
			$('#actualGenerate').click(function() {
				var monthYear = $target.val();
				if(monthYear != '') {
					$('#generateModal').modal('hide');
					window.location = '${home}/expense/generate?monthYear=' + monthYear;
				}
			});
			
			$.fn.typeahead.Constructor.prototype.process = function (items) {
	        	var that=this;
	        	items=$.grep(items,	function(item){
        			return that.matcher(item.tin);
        		}),
	        	items.length ? this.render(items.slice(0,this.options.items)).show() : this.shown?this.hide() : this
	        };
	        $.fn.typeahead.Constructor.prototype.render = function(items) {
	        	var that = this;
	            items = $(items).map(function (i, item) {
	            	var query = that.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&');
	                i = $(that.options.item).attr('data-name', item.display);
	                i.data('receipt', item);
	                i.find('a').html(item.display.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
	                    return '<strong>' + match + '</strong>';
	                }));
	                return i[0];
	            });

	            items.first().addClass('active');
	            this.$menu.html(items);
	            if(items.length > 0) {
	            	var height = 230 + items.length*25;
	            	$("#form-body .modal-body").css("height", height + "px");
	            }
	            return this;
	        };
	        
	        $.fn.typeahead.Constructor.prototype.select = function(items) {
	        	var $selectedItem = this.$menu.find('.active');
	            this.$element.val($selectedItem.text()).change();
	            $("#form-body .modal-body").css("height", "");
	            var receipt = $selectedItem.data('receipt');
	            $("#form-body #receipt").val(receipt.id);
	            $('#form-body #tempReceipt\\.name').val(receipt.name);
	            $('#form-body #tempReceipt\\.name').attr('readonly', true);
	            $('#form-body #tempReceipt\\.address1').val(receipt.address1);
	            $('#form-body #tempReceipt\\.address1').attr('readonly', true);
	            $('#form-body #tempReceipt\\.address2').val(receipt.address2);
	            $('#form-body #tempReceipt\\.address2').attr('readonly', true);
	            $("#tinSelect2 div").html($selectedItem.text());
	            $("#tinSelect2").css("display",'inline-block');
	            $("#tinSelect2 a.select2-search-choice-close").click(function() {
	            	$("#form-body #parent").val("");
	            	$("#tinSelect2").css("display","none");
	            	$("#form-body #receipt").val("");
	            	$("#form-body #tempReceipt\\.tin").val("");
	            	$("#form-body #tempReceipt\\.tin").show();
	            	$('#form-body #tempReceipt\\.name').val("");
		            $('#form-body #tempReceipt\\.name').attr('readonly', false);
		            $('#form-body #tempReceipt\\.address1').val('');
		            $('#form-body #tempReceipt\\.address1').attr('readonly', false);
		            $('#form-body #tempReceipt\\.address2').val('');
		            $('#form-body #tempReceipt\\.address2').attr('readonly', false);
	            });
	            this.$element.hide();
				this.$menu.hide();
	            this.shown = false;
	        };
	        $('#form-body #tempReceipt\\.tin').change(function(){
	        	
	        });
			$('#form-body #tempReceipt\\.tin').typeahead({
				minLength: 3,
				source: function (query, process) {
					$.ajax({
		                type: "GET",
		                url: '${home}/receipt/byTin',
		                data: { value: $('#form-body #tempReceipt\\.tin').val() },
		                success: function(result) {
		                	map = {};
		                	var results = [];
		                	$.each(result,function(index, item){
		                		results.push(item.tin);
		                		map[item.tin + " - " + item.name] = item;
		                	});
		    				process(result);
		    				return result;
		                },
		            });
				}
		    });
		});
	</script>
</tides:footer>