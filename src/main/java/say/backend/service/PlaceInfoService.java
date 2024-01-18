package say.backend.service;

import say.backend.domain.common.DelYn;
import say.backend.domain.file.PlaceFileRepository;
import say.backend.domain.place.PlaceCategory;
import say.backend.domain.place.PlaceInfo;
import say.backend.domain.place.PlaceInfoRepository;

import say.backend.dto.place.*;
import say.backend.exception.common.BusinessException;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import say.backend.exception.common.ErrorCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PlaceInfoService {

    private final PlaceInfoRepository placeInfoRepository;

    @Transactional
    public PlaceInfo createPlace(PlaceCreateDto pcd) {
        try{
            // create new obj
            PlaceInfo newPlaceInfo = new PlaceInfo();

            // set param
            String uuid = UUID.randomUUID().toString();
            LocalDateTime date = LocalDateTime.now();
            newPlaceInfo.setPlaceIdx(uuid);
            newPlaceInfo.setPlaceName(pcd.getPlaceName());
            newPlaceInfo.setAddress(pcd.getAddress());
            newPlaceInfo.setAddressDetail(pcd.getAddressDetail());
            newPlaceInfo.setPlaceCategory(pcd.getPlaceCategory());
            newPlaceInfo.setCoordinate(pcd.getCoordinate());
            newPlaceInfo.setRegDt(date);
            newPlaceInfo.setModDt(date);
            newPlaceInfo.setDelYn(DelYn.N);

            // insert new data
            PlaceInfo resultData = placeInfoRepository.save(newPlaceInfo);

            return resultData;
        } catch(Exception e){
            throw new BusinessException(ErrorCode.DATABASE_ERROR);
        }
    }

    public PlaceInfo getPlaceDetail(String placeIdx) {
        try{
            // get data
            Optional<PlaceInfo> resultData = placeInfoRepository.findByPlaceIdx(placeIdx);

            // check empty value
            if(resultData.isEmpty()) {
                throw new BusinessException(ErrorCode.NO_EXIST_VALUE);
            }

            // return
            return resultData.get();
        } catch (BusinessException e){
            throw e;
        } catch(Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR);
        }

    }

    public List<PlaceInfo> getPlaceList(String placeName, PlaceCategory placeCategory) {
        try {
            List<PlaceInfo> resultData = placeInfoRepository
                    .findByPlaceNameAndPlaceCategoryAndDelYn(placeName, placeCategory, DelYn.N);

            // check empty data
            if(resultData.isEmpty()) {
                throw new BusinessException(ErrorCode.NO_EXIST_VALUE);
            }

            // return
            return resultData;
        } catch (IllegalArgumentException e) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        } catch (BusinessException e){
            throw e;
        } catch(Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR);
        }
    }

    public List<PlaceInfo> getPlaceList(String placeName, List<PlaceCategory> placeCategoryList) {
        try {
            List<PlaceInfo> resultData = placeInfoRepository
                    .findByPlaceNameAndPlaceCategoryListAndDelYn(placeName, placeCategoryList, DelYn.N);

            // check empty data
            if(resultData.isEmpty()) {
                throw new BusinessException(ErrorCode.NO_EXIST_VALUE);
            }

            // return
            return resultData;
        } catch (IllegalArgumentException e) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        } catch (BusinessException e){
            throw e;
        } catch(Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Transactional
    public PlaceInfo updatePlace(String pIdx, String pName, String addr, String addrDetail, PlaceCategory pCategory, String coordinate) {
        try {
            Optional<PlaceInfo> updateData = placeInfoRepository.findByPlaceIdx(pIdx);
            if(updateData.isEmpty()) {
                throw new BusinessException(ErrorCode.NO_EXIST_VALUE);
            }

            if (pName != null) { updateData.get().setPlaceName(pName); }
            if (addr != null) { updateData.get().setAddress(addr); }
            if (addrDetail != null) { updateData.get().setAddressDetail(addrDetail); }
            if (pCategory != null) { updateData.get().setPlaceCategory(pCategory); }
            if (coordinate != null) { updateData.get().setCoordinate(coordinate); }
            updateData.get().setModDt(LocalDateTime.now());

            return updateData.get();
        } catch(BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR);
        }
    }

    @Transactional
    public PlaceInfo deletePlace(String placeIdx) {
        try {
            Optional<PlaceInfo> delData = placeInfoRepository.findByPlaceIdx(placeIdx);
            if(delData.isEmpty()) {
                throw new BusinessException(ErrorCode.NO_EXIST_VALUE);
            }
            delData.get().setDelYn(DelYn.Y);
            delData.get().setModDt(LocalDateTime.now());
            return delData.get();
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR);
        }
    }
}
