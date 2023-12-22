<template>
	<view>
		<view class="customer-container">
			<u-avatar :src="orderDetail.driverPhoto" mode="square"></u-avatar>
			<view class="info">
				<view class="customer-name">{{ orderDetail.driverName }}（司机）</view>
				<view class="customer-tel">Tel：{{ orderDetail.driverPhone }}</view>
			</view>
		</view>
		<view class="address-container">
			<view class="from">
				<text>{{ orderDetail.startPlace }}</text>
			</view>
			<view class="dashed-line"></view>
			<view class="to">
				<text>{{ orderDetail.endPlace }}</text>
			</view>
		</view>
		<view class="order-container">
			<view>【 订单号码 】 {{ orderDetail.orderNo }}</view>
			<view>【 下单时间 】 {{ orderDetail.createTime }}</view>
			<view>【 客户红包 】 {{ orderDetail.favourFee }}元</view>
			<!-- <view>【 系统奖励 】 {{ orderDetail.incentiveFee }}元</view> -->
			<view>【 代驾车型 】 {{ orderDetail.carType }}</view>
			<view>【 代驾车牌 】 {{ orderDetail.carPlate }}</view>
		</view>
		<view>
			<view class="setion-title">
				<image src="../../static/order/money.png" mode="widthFix"></image>
				<text>基础收费</text>
			</view>
			<view class="section-content">
				<view class="item">
					<view class="left">
						<text class="item-title">里程距离（{{ orderDetail.realMileage }}公里）</text>
						<text class="item-desc">时段收费（{{ orderDetail.baseMileagePrice }}元{{ orderDetail.baseMileage }}公里，超出每公里{{ orderDetail.exceedMileagePrice }}元）</text>
					</view>
					<view class="right">费用：{{ orderDetail.mileageFee }}</view>
				</view>
				<view class="item">
					<view class="left">
						<text class="item-title">等待时长（{{ orderDetail.waitingMinute }}分钟）</text>
						<text class="item-desc">免费{{ orderDetail.baseWaitingMinute }}分钟，超出部分每分钟{{ orderDetail.exceedWaitingMinutePrice }}元</text>
					</view>
					<view class="right">费用：{{ orderDetail.waitingFee }}</view>
				</view>
				<view class="item">
					<view class="left">
						<text class="item-title">返程（{{ orderDetail.returnMileage }}公里）</text>
						<text class="item-desc">总里程超过{{ orderDetail.baseReturnMileage }}公里，每公里{{ orderDetail.exceedBaseReturnMileagePrice }}元</text>
					</view>
					<view class="right">{{ orderDetail.returnFee }}</view>
				</view>
			</view>
		</view>
		<view>
			<view class="setion-title">
				<image src="../../static/order/money.png" mode="widthFix"></image>
				<text>额外收费</text>
			</view>
			<view class="section-content">
				<view class="item">
					<view class="left">
						<text class="item-title">停车费</text>
						<text class="item-desc">如果代驾司机预付停车费，该费用将计入订单费用</text>
					</view>
					<view class="right">{{ orderDetail.parkingFee }}</view>
				</view>
				<view class="item">
					<view class="left">
						<text class="item-title">路桥费</text>
						<text class="item-desc">如果代驾司机预付停车费，该费用将计入订单费用</text>
					</view>
					<view class="right">{{ orderDetail.tollFee }}</view>
				</view>
				<view class="item">
					<view class="left">
						<text class="item-title">其他费用</text>
						<text class="item-desc">代驾过程中产生的其他费用</text>
					</view>
					<view class="right">{{ orderDetail.otherFee }}</view>
				</view>
			</view>
		</view>
		<view>
			<view class="setion-title">
				<image src="../../static/order/money.png" mode="widthFix"></image>
				<text>总金额</text>
			</view>
			<view class="section-content">
				<view class="content-container">
					<view class="left">
						<view class="content">
							【汇总合计】
							<text>¥ {{ orderDetail.total }} 元</text>
						</view>
						<view class="content">
							【减免金额】
							<text>¥ {{ orderDetail.voucherFee }} 元</text>
						</view>
						<view class="content">
							【实付金额】
							<text class="red">¥ {{orderDetail.realPay }} 元</text>
						</view>
					</view>
					<image :src="img" mode="widthFix" class="img"></image>
				</view>
			</view>
		</view>

		<view class="operate-container" v-show="orderDetail.status == 6">
			<button class="btn" @tap="showPay">立即付款</button>
		</view>
		<view v-if="orderDetail.status >= 7">
			<view class="setion-title">
				<image src="../../static/order/rate.png" mode="widthFix"></image>
				<text>客户评价</text>
			</view>
			<view class="section-content">
				<view class="remark-container">
					<view class="remark-rate">
						<view class="photo">
							<u-avatar :src="photo" size="60" />
						</view>
						<view class="rate">
							<u-rate :count="rateCount" 
									v-model="customerComment.rate" disabled="true" 
									active-color="#FFBB2A" size="40" />
						</view>
					</view>
					<view class="remark">{{ customerComment.remark }}</view>
				</view>
			</view>
		</view>

		<view class="contact-container">
			<view class="contact">
				<text class="label">客服电话：</text>
				<text class="tel">0411-11111111</text>
			</view>
			<view class="contact">
				<text class="label">服务监督电话：</text>
				<text class="tel">0411-11111111</text>
			</view>
		</view>
		<u-top-tips ref="uTips"></u-top-tips>
		<u-popup v-model="comment.showComment" mode="center" 
				border-radius="14" width="550rpx" height="580rpx">
			<view class="comment-title">请您为司机的服务打分</view>
			<view class="comment-desc">请给出你最真实的评价~</view>
			<view class="comment-rate">
				<u-rate :count="rateCount" v-model="comment.rate" 
						active-color="#FFBB2A" size="40">
				</u-rate>
			</view>
			<u-input v-model="comment.remark" type="textarea" :border="false"
					:clearable="false" placeholder="来吧,讲两句呗~" 
					:custom-style="comment.remarkStyle" />
			<u-button type="success" :custom-style="comment.btnStyle" 
						@click="insertComment">确定</u-button>
		</u-popup>
		
		
		<u-popup v-model="showPayPopup" mode="bottom" mask="false">
			<view>
				
				<u-button type="success" @click="wechatPay" :custom-style="comment.btnStyle">
					<u-icon name="chat-fill" color="#2979ff" size="40"></u-icon>
					微信支付
				</u-button>
			</view>
			<view>
				<u-button type="success" @click="walletPay" :custom-style="comment.btnStyle">
					<u-icon name="rmb-circle" color="#2979ff" size="40"></u-icon>
					钱包支付
				</u-button>
			</view>
		</u-popup>
	</view>
</template>

<script>

import WebIM from "../../lib/huanxin.js"
export default {
	data() {
		return {
			showPayPopup:false,
			orderDetail:{
				name: "王师傅",
			},
			orderNo: null,
			img: '',
			rateCount:5,
			comment: {
				showComment: false,
				rate: 0,
				remark: null,
				remarkStyle: {
					'background-color': '#f8f8f8',
					padding: '20rpx',
					margin: '0 40rpx'
				},
				btnStyle: {
					margin: '40rpx 40rpx 30rpx 40rpx'
				}
			},
			customerComment: {
				rate: 5,
				remark: '[ 默认为五星好评 ]'
			}
		};
	},
	methods: {
		

	},
	onLoad: function(options) {
		let _this = this;
		_this.orderNo = options.orderNo;
		//_this.initHuanxin();
	}
};
</script>

<style lang="less">
@import url('order.less');
</style>
