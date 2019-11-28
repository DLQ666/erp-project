//提交的方法
var method = "";
	$(function() {
		$('#grid').datagrid({    
		    url:name+'_getList',    
		    columns:columns,
		    pagination:true,
		    singleSelect:true,
		    toolbar: [{
		    	text:'新增',
				iconCls: 'icon-add',
				handler: function(){
					method = "add";
					//添加完事后清空当前表单数据，下次添加时不会出现之前添加过的表单数据
					$('#editForm').form('clear');
					$('#editDlg').dialog('open');
				}
			}]
		});
		
		$('#btnSearch').bind('click',function() {
			//表单searchForm的数据转换为json对象
			var formData=$('#searchForm').serializeJSON();
			//json.stringify用于将json对象转换为json字符串
			//alert(JSON.stringify(formData));
			$('#grid').datagrid('load',formData);
		});
		$('#btnSearchClear').bind('click',function() {
				$('#searchForm').form('clear');
				var formData=$('#searchForm').serializeJSON();
				$('#grid').datagrid('load',formData);
		});
		
		$('#editDlg').dialog({    
		    title: '部门编辑',
		    width: 350,
		    height: 200,
		    closed: true, //窗口是否未关闭状态，true：表示关闭    
		    modal: true
		});   
		
		$('#btnSave').bind('click',function() {
			var formData = $('#editForm').serializeJSON();
			$.ajax({
				url: name+'_' + method,
				data: formData,
				dataType: 'json',
				type: 'post',
				success: function(rtn){
					$.messager.alert("提示",rtn.message,'info',function(){
						//成功的话我们要关闭窗口
						$('#editDlg').dialog('close');
						//刷新表格数据
						$('#grid').datagrid('reload');
					});
				}
			})
		})
	});
	function del(uuid) {
		$.messager.confirm('确认', '确认要删除吗？', function(ok){
			if (ok){
				$.ajax({
					url: name+'_delete?id=' + uuid,
					dataType: 'json',
					type: 'post',
					success: function(rtn){
						$.messager.alert("提示",rtn.message,'info',function(){
							//刷新表格数据
							$('#grid').datagrid('reload');
						});
					}
				})
			    // 退出操作;
			}
		});

	}
/**
 * 修改部门
 */
 function edit(uuid){
	//弹出窗口
	$('#editDlg').dialog('open');
	//清空表单内容
	$('#editForm').form('clear');
	method = "update";
	//加载数据
	$('#editForm').form('load',name+'_get?id=' + uuid);
}