<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<!-- chart cdn -->
<script
src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
</script>

<script>
	$(document).ready(function(){
		//동기호출로 x와 y 값을 셋팅한다
		const x = [];
		const y = [];
		$.ajax({
			async : false, //true (비동기, 기본값이다), false(동기)
			url : '/rest/localNameList',
			type : 'get',
			success : function(model){ //실행시키면서 데이터를 뿌려준다 // 동기/비동기 상관 없음
				//backend 모델 -> front 모델
				//model ->{'model' : [{localName:'지역1'}, {cnt : 10}
				//						...					]}
				model.forEach(function(item, index){	//for(in){} = foreach문
					$('#target').append('<tr>');
					$('#target').append('<td>'+item.localName+'</td>'); //x
					$('#target').append('<td>'+item.cnt+'</td>'); //y
					$('#target').append('</tr>');
					//chart 모델 생성
					//원래는 map 타입의 리스트였지만 각 타입마다 배열이 생성
					x.push(item.localName);
					y.push(item.cnt);
				});
				
			}
		});
		//차트로 보이기 위해선 localName과 cnt를 각각의 배열로 만들어서 -> x축 y축
		
		//const barColors = ["red", "green","blue","orange","brown"];

		new Chart("target2", {
		  type: "bar",
		  data: {
		    labels: x,
		    datasets: [{
		      //backgroundColor: barColors,
		      data: y
		    }]
		  },
		  //options: {...}
		});
		
	});
</script>
</head>
<body>
<!-- ajax 코드 -->
<!-- pom.xml or cdn 주소 -->
	<h1>AJax API 사용으로 localNameList 가져오기</h1>
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>게시글 수</th>
			</tr>
		</thead>
		<tbody id="target">
		</tbody>
	</table>
	<canvas id="target2" style="width:100%;max-width:800px"></canvas>

</body>
</html>