<template>
	<view>
		<view class="address-container">
			<view class="from">
				<text>{{ from.address }}</text>
			</view>
			<view class="dashed-line"></view>
			<view class="to">
				<text>{{ to.address }}</text>
			</view>
		</view>
		<view v-if="inPorcessOrderId != null" class="order-process" @click="toInProcessOrder">您有进行中的订单,点击查看</view>
		<map
			id="map"
			:longitude="from.longitude"
			:latitude="from.latitude"
			:style="contentStyle"
			scale="16"
			:enable-traffic="false"
			:show-location="true"
			class="map"
			:polyline="polyline"
			:markers="markers"
		></map>

		<view class="panel">
			<view class="row">
				<view class="info">
					<view class="label">全程</view>
					<view class="value">
						<text>{{ distance }}</text>
						公里
					</view>
					<view class="label">，预计</view>
					<view class="value">
						<text>{{ duration }}</text>
						分钟
					</view>
				</view>
				<view class="opt" @tap="chooseCarHandle" v-if="!showCar">选择车辆</view>
				<view class="opt" @tap="chooseCarHandle" v-if="showCar">{{ car.carType }}{{ car.carPlate }}</view>
			</view>
			<button class="btn" @tap="createOrderHandle">确定下单</button>
		</view>
		<u-popup v-model="showPopup" mode="center" width="600" height="280" :mask-close-able="false">
			<view class="popup-title">您的订单正在等待司机接单</view>
			<view class="count-down">
				<u-count-down
					ref="uCountDown"
					:timestamp="timestamp"
					:autoplay="false"
					separator="zh"
					:show-hours="false"
					:show-border="true"
					bg-color="#DDF0FF"
					separator-color="#0083F3"
					border-color="#0D90FF"
					color="#0D90FF"
					font-size="32"
					@end="countEndHandle"
					@change="countChangeHandle"
				></u-count-down>
				<!-- @change="countChangeHandle" -->
				
			</view>
			<button class="btn" @tap="cancelHandle">取消订单</button>
		</u-popup>
		<u-top-tips ref="uTips"></u-top-tips>
		<u-modal v-model="uModalShow" 
		show-confirm-button="继续等待"
		show-cancel-button="确定取消"
		@confirm="confirm" ref="uModal" :async-close="true"></u-modal>

	</view>
</template>

<script>
let QQMapWX = require('../../lib/qqmap-wx-jssdk.min.js');
let qqmapsdk;
export default {
	data() {
		return {
			//订单路线：开始位置和结束位子
			from: {
				address: '',
				longitude: 0,
				latitude: 0
			},
			to: {
				address: '',
				longitude: 0,
				latitude: 0
			},
			//窗口样式
			contentStyle: '',
			windowHeight: 0,
			//距离和时长
			distance: 0,
			duration: 0,
			//路线规划，路线坐标和图标
			polyline: [],
			markers: [],
			
			//
			infoStatus: true,
			car:{
				//乘客选择的车辆
				carId: null,
				carPlate: null,
				carType: null,
			},
			showCar: false,
			showPopup: false,
			timestamp: 60 * 60,
			timestampStaring:false,
			orderNo: null,
			//地图组件
			map:null,
			//接单的定时任务
			acceptOrderTimer:null,
			uModalShow:false,
			//是否有进行中的订单
			inPorcessOrderId:null
		};
	},
	methods: {
		//初始化窗口样式
		initStyle(){
			let _this = this;
			//处理窗口样式
			let windowHeight = uni.getSystemInfoSync().windowHeight;
			_this.windowHeight = windowHeight;
			_this.contentStyle = `height:${_this.windowHeight}px;`;
		},
		
	},
	onLoad: function(options) {
		let _this = this;
		
		_this.initStyle();
	},
	onShow: function() {
	},
	onUnload() {
	}
};
</script>

<style lang="less">
@import url('create_order.less');

</style>
