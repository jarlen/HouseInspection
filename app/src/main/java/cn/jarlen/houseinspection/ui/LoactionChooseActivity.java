package cn.jarlen.houseinspection.ui;

import android.widget.TextView;

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
import cn.jarlen.richcommon.log.Log;
import cn.jarlen.richcommon.utils.ToastUtil;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/7.
 */

public class LoactionChooseActivity extends BKBaseActivity {

    @BindView(R.id.mapview)
    MapView mapView;

    @BindView(R.id.location_info)
    TextView locationInfo;

    private BaiduMap mBaiduMap;

    private double mLatitude;
    private double mLongitude;

    private LatLng defaultLatLng = new LatLng(113.523624,34.817154);

    private LocationClient locationClient;
    private BDLocationListener listener = new MyLocationListener();

    @Override
    protected void onBKBindView() {

        mBaiduMap = mapView.getMap();
        mapView.showZoomControls(true);
        MapStatus mapStatus = new MapStatus.Builder().target(defaultLatLng).zoom(18).build();
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
//        locationClient = new LocationClient(getApplicationContext());
//        locationClient.registerLocationListener(listener);
//        LocationUtil.initLocation(locationClient);
//        locationClient.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location_choose;
    }


    // 根据经纬度查询位置
    private void getInfoFromLAL(final LatLng point) {

        locationInfo.setText("经度：" + point.latitudeE6 + "，纬度" + point.latitudeE6);
        GeoCoder gc = GeoCoder.newInstance();
        gc.reverseGeoCode(new ReverseGeoCodeOption().location(point));
        gc.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Log.e("发起反地理编码请求", "未能找到结果");
                } else {
                    locationInfo.setText("经度：" + point.latitudeE6 + "，纬度" + point.latitudeE6
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
        mLatitude = point.latitude;
        mLongitude = point.longitude;

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
