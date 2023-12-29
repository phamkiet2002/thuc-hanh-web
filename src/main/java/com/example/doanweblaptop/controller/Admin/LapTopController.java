package com.example.doanweblaptop.controller.Admin;

import com.example.doanweblaptop.dto.LapTopDTO;
import com.example.doanweblaptop.entity.DanhMuc;
import com.example.doanweblaptop.entity.LapTop;
import com.example.doanweblaptop.entity.NhaCungCap;
import com.example.doanweblaptop.service.DanhMuc.DanhMucService;
import com.example.doanweblaptop.service.Laptop.LapTopService;
import com.example.doanweblaptop.service.NhaCungCap.NhaCungCapService;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/laptops")
public class LapTopController {
    private LapTopService lapTopService;
    private NhaCungCapService nhaCungCapService;
    private DanhMucService danhMucService;

    public LapTopController(LapTopService lapTopService,
                            NhaCungCapService nhaCungCapService, DanhMucService danhMucService) {
        this.lapTopService = lapTopService;
        this.nhaCungCapService = nhaCungCapService;
        this.danhMucService = danhMucService;
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        LapTopDTO laptopDTO= new LapTopDTO();
        model.addAttribute("laptop",laptopDTO);
        List<NhaCungCap> nhaCungCaps= nhaCungCapService.findAll();
        model.addAttribute("nhacungcap",nhaCungCaps);
        List<DanhMuc> danhMucs=danhMucService.findAll();
        model.addAttribute("danhmuc",danhMucs);
        model.addAttribute("action","/laptops/save");
        return "form/formThemSuaLaptop";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("laptop") LapTopDTO lapTopDTO,Model model) {
        Optional<LapTop> opt =lapTopService.findById(lapTopDTO.getId());
        String image=null;
        Path path = Paths.get("images/");
        if (opt.isPresent()){
            if (lapTopDTO.getImage().isEmpty()){
                image=opt.get().getImage();
            }
            else {
                try {
                    InputStream inputStream=lapTopDTO.getImage().getInputStream();
                    Files.copy(inputStream,path.resolve(lapTopDTO.getImage().getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                    image=lapTopDTO.getImage().getOriginalFilename().toString();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else {
            if(!lapTopDTO.getImage().isEmpty()){
                    try {
                        InputStream inputStream=lapTopDTO.getImage().getInputStream();
                        Files.copy(inputStream,path.resolve(lapTopDTO.getImage().getOriginalFilename()),
                                StandardCopyOption.REPLACE_EXISTING);
                        image= lapTopDTO.getImage().getOriginalFilename().toString();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
        }
        NhaCungCap nhaCungCap=nhaCungCapService.findById(lapTopDTO.getNcc().getId());
        DanhMuc danhMuc=danhMucService.findById(lapTopDTO.getDanhMuc().getId());
        LapTop lapTop=new LapTop(lapTopDTO.getId(),lapTopDTO.getName(),lapTopDTO.getPrice(),lapTopDTO.getType(),lapTopDTO.getQuantity(),image,lapTopDTO.getDescription(),nhaCungCap,lapTopDTO.getDanhGias(),danhMuc);
        lapTopService.save(lapTop);
        return "redirect:/admin/listlaptop";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("laptopid") Long laptopid) {
        lapTopService.deleteById(laptopid);
        return "redirect:/admin/listlaptop";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("laptopid") Long id, Model model){
        Optional<LapTop> opt=lapTopService.findById(id);
        LapTopDTO laptopDTO=null;
        if(opt.isPresent()){
            LapTop laptop=opt.get();
            File file=new File("images/"+laptop.getImage());
            FileInputStream input;
            try {
                input=new FileInputStream(file);
                MultipartFile multipartFile=
                        new MockMultipartFile("file",file.getName(),
                                "text/plain", IOUtils.toByteArray(input));
                laptopDTO=new LapTopDTO(laptop.getId(),laptop.getName(),laptop.getPrice(),laptop.getType(),laptop.getQuantity(),multipartFile,laptop.getDescription(),laptop.getNcc(),laptop.getDanhMuc(),laptop.getDanhGias());
                laptopDTO.setId(laptop.getId());
                //              System.out.println("id la "+employeeDTO.getId());
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            model.addAttribute("laptop",laptopDTO);
        }else {
            model.addAttribute("laptop",new LapTopDTO());
        }
        List<NhaCungCap> nhaCungCaps= nhaCungCapService.findAll();
        model.addAttribute("nhacungcap",nhaCungCaps);
        List<DanhMuc> danhMucs=danhMucService.findAll();
        model.addAttribute("danhmuc",danhMucs);
        model.addAttribute("action","/laptops/save");
        return "form/formThemSuaLaptop";
    }
}
