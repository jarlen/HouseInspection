package cn.jarlen.houseinspection.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import butterknife.BindView;
import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.houseinspection.data.LocationInfo;
import cn.jarlen.houseinspection.utils.LocationUtil;
import cn.jarlen.richcommon.log.Log;
import cn.jarlen.richcommon.utils.ToastUtil;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/7.
 */

public class LoactionChooseActivity extends BKBaseActivity {

    @BindView(R.id.mapview)
    MapView mapView;

    @BindView(R.id.locationtv)
    TextView locationTv;

    private BaiduMap mBaiduMap;

    private LocationInfo locationInfo;

    private LatLng defaultLatLng = new LatLng(34.817154,113.523624);

    private LocationClient locationClient;
    private BDLocationListener listener = new MyLocationListener();

    @Override
    protected void onBKBindView() {
        showBackView();
        showTitleView(R.string.location_choose);

        showRightText(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent result = new Intent();
                result.putExtra("data",locationInfo);
                setResult(Activity.RESULT_OK,result);
                finish();
            }
        });

        mBaiduMap = mapView.getMap();
        View child = mapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)){
            child.setVisibility(View.INVISIBLE);
        }
        mapView.showScaleControl(false);
        mapView.showZoomControls(false);
        MapStatus mapStatus = new MapStatus.Builder().target(defaultLatLng).zoom(16).build();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatus));
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                setMapOverlay(latLng);
                getInfoFromLAL(latLng);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
        setMapOverlay(defaultLatLng);
        getInfoFromLAL(defaultLatLng);
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(listener);
        LocationUtil.initLocation(locationClient);
        locationClient.start();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location_choose;
    }


    // 根据经纬度查询位置
    private void getInfoFromLAL(final LatLng point) {
        if(locationInfo == null){
            locationInfo = new LocationInfo();
        }

        locationInfo.setLongitude(point.longitude);
        locationInfo.setLatitude(point.latitude);

        locationTv.setText("经度：" + point.latitudeE6 + "\n纬度：" + point.latitudeE6);
        locationTv.setVisibility(View.VISIBLE);
        GeoCoder gc = GeoCoder.newInstance();
        gc.reverseGeoCode(new ReverseGeoCodeOption().location(point));
        gc.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Log.e("发起反地理编码请求", "未能找到结果");
                } else {
                    locationInfo.setLocationDesc(result.getAddress());
                    locationTv.setText("经度：" + point.latitudeE6 + "\n纬度：" + point.latitudeE6
                            + "\n" + result.getAddress());
                }
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {

            }
        });
    }


    // 在地图上添加标注
    private void setMapOverlay(LatLng point) {
        if(locationInfo == null){
            locationInfo = new LocationInfo();
        }
        mBaiduMap.clear();
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_mark);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        mBaiduMap.addOverlay(option);
    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation != null && (bdLocation.getLocType() == 161 || bdLocation.getLocType() == 66)) {
                //这里得到BDLocation就是定位出来的信息了
            } else {
                ToastUtil.makeToast(LoactionChooseActivity.this).setText("定位失败，请检查手机网络或设置").show();
            }
        }
    }


}
